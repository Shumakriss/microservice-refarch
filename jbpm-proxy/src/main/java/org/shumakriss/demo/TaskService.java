package org.shumakriss.demo;

import org.kie.server.api.model.definition.TaskInputsDefinition;
import org.kie.server.api.model.definition.TaskOutputsDefinition;
import org.kie.server.api.model.instance.ProcessInstance;
import org.kie.server.api.model.instance.TaskInstance;
import org.kie.server.api.model.instance.TaskSummary;
import org.kie.server.client.ProcessServicesClient;
import org.kie.server.client.QueryServicesClient;
import org.kie.server.client.UserTaskServicesClient;
import org.shumakriss.demo.data.Task;
import org.shumakriss.demo.data.TaskDatum;
import org.shumakriss.demo.data.Tasklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TaskService {

    public static final String DEFAULT_USER = "kieserver";

    @Autowired
    ProcessServicesClient processClient;

    @Autowired
    QueryServicesClient queryClient;

    @Autowired
    UserTaskServicesClient taskClient;


    Map<String, String> getTypeMappings(){
        Map<String, String> mappings = new HashMap<String, String>();
        mappings.put("Work_Product", "TextboxQuestion");
        mappings.put("Second_Actor", "DropdownQuestion");
        mappings.put("Approve", "CheckboxQuestion");
        return mappings;
    }

    List<TaskDatum> mergeTaskContentWithType(Task task, Map<String, String> userTaskInputDefinitions, Map<String, Object> taskInputContentByTaskId){
        List<TaskDatum> inData = new ArrayList<TaskDatum>();
        for( String key: userTaskInputDefinitions.keySet()){
            if("TaskName".equals(key) || "Skippable".equals(key))
                continue;
            TaskDatum taskDatum = new TaskDatum();
            taskDatum.setName(key);
            taskDatum.setType(getTypeMappings().get(key));

            if(taskInputContentByTaskId.containsKey(key))
                taskDatum.setValue(taskInputContentByTaskId.get(key));

            inData.add(taskDatum);
        }

        return inData;
    }

    String lookupTrackingNumber(Task task){
        return processClient.getProcessInstance(task.getContainerId(), task.getProcessInstanceId()).getCorrelationKey();
    }

    void addTaskDetail(Task task){
        String containerId = task.getContainerId();
        String processId = task.getProcessId();
        Long taskId = task.getId();
        String name = task.getName();

        Map<String, String> userTaskInputDefinitions = processClient.getUserTaskInputDefinitions(containerId, processId, name).getTaskInputs();
        Map<String, Object> taskInputContentByTaskId = taskClient.getTaskInputContentByTaskId(containerId, taskId);
        task.setInData(mergeTaskContentWithType(task, userTaskInputDefinitions, taskInputContentByTaskId));

        Map<String, String> userTaskOutputDefinitions = processClient.getUserTaskOutputDefinitions(containerId, processId, name).getTaskOutputs();
        Map<String, Object> taskOutputContentByTaskId = taskClient.getTaskOutputContentByTaskId(containerId, taskId);
        task.setOutData(mergeTaskContentWithType(task, userTaskOutputDefinitions, taskOutputContentByTaskId));

        task.setTrackingNumber(lookupTrackingNumber(task));

        if(task.getDescription() == null || task.getDescription().isEmpty())
            task.setDescription("No description available.");
    }


    Task createTaskFromTaskSummary(TaskSummary summary) {
        Task task = new Task();

        task.setId(summary.getId());
        task.setName(summary.getName());
        task.setSubject(summary.getSubject());
        task.setDescription(summary.getDescription());
        task.setStatus(summary.getStatus());
        task.setPriority(summary.getPriority());
        task.setSkipable(summary.getSkipable());
        task.setActualOwner(summary.getActualOwner());
        task.setCreatedBy(summary.getCreatedBy());
        task.setCreatedOn(summary.getCreatedOn());
        task.setActivationTime(summary.getActivationTime());
        task.setExpirationDate(summary.getExpirationTime());
        task.setProcessInstanceId(summary.getProcessInstanceId());
        task.setProcessId(summary.getProcessId());
        task.setContainerId(summary.getContainerId());
        task.setParentId(summary.getParentId());

        return task;
    }

    Task createTaskFromTaskInstance(TaskInstance instance) {
        Task task = new Task();

        task.setId(instance.getId());
        task.setName(instance.getName());
        task.setSubject(instance.getSubject());
        task.setDescription(instance.getDescription());
        task.setStatus(instance.getStatus());
        task.setPriority(instance.getPriority());
        task.setSkipable(instance.getSkipable());
        task.setActualOwner(instance.getActualOwner());
        task.setCreatedBy(instance.getCreatedBy());
        task.setCreatedOn(instance.getCreatedOn());
        task.setActivationTime(instance.getActivationTime());
        task.setExpirationDate(instance.getExpirationDate());
        task.setProcessInstanceId(instance.getProcessInstanceId());
        task.setProcessId(instance.getProcessId());
        task.setContainerId(instance.getContainerId());
        task.setParentId(instance.getParentId());

        return task;
    }


    Map<String, Object> getContentFromTaskData(List<TaskDatum> taskData) {
        Map<String, Object> content = new HashMap<String, Object>();
        for( TaskDatum datum : taskData)
            content.put(datum.getName(), datum.getValue());
        return content;
    }

    public Task getTaskDetail(Long taskId) {
        TaskInstance taskInstance = taskClient.findTaskById(taskId);
        Task task = createTaskFromTaskInstance(taskInstance);
        addTaskDetail(task);
        System.out.println(task);
        return task;
    }

    public void updateProcessVariables(Long taskId, Task task) {
        Map<String, Object> content = getContentFromTaskData(task.getOutData());
        taskClient.saveTaskContent(task.getContainerId(), task.getId(), content);
    }

    public void completeTask(Long taskId, Task task) {
        Map<String, Object> content = getContentFromTaskData(task.getOutData());
        taskClient.completeAutoProgress(task.getContainerId(), task.getId(), DEFAULT_USER, content);
    }

    public Tasklist getTasks(String term) {
        if(term != null)
            term = term.toLowerCase();

        List<Task> tasks = new ArrayList<Task>();
        List<TaskSummary> taskSummaryList = taskClient.findTasks(DEFAULT_USER, 0, 100);

        for(TaskSummary taskSummary : taskSummaryList) {
            Task task = createTaskFromTaskSummary(taskSummary);
            addTaskDetail(task);
            if( term == null || (term != null && termMatchesTask(task, term)) )
                tasks.add(task);
        }

        Tasklist tasklist = new Tasklist();
        tasklist.setTasks(tasks);
        return tasklist;
    }

    boolean termMatchesTask(Task task, String term){
        return (String.valueOf(task.getId()).equals(term) ||
                task.getName().toLowerCase().contains(term) ||
                task.getStatus().toLowerCase().contains(term) ||
                task.getTrackingNumber().toLowerCase().contains(term));
    }
}

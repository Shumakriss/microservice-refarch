package org.shumakriss.demo;

import org.kie.api.task.model.TaskData;
import org.kie.server.api.model.definition.TaskInputsDefinition;
import org.kie.server.api.model.definition.TaskOutputsDefinition;
import org.kie.server.api.model.instance.TaskInstance;
import org.kie.server.api.model.instance.TaskSummary;
import org.kie.server.client.ProcessServicesClient;
import org.kie.server.client.QueryServicesClient;
import org.kie.server.client.UserTaskServicesClient;
import org.shumakriss.demo.data.Task;
import org.shumakriss.demo.data.TaskDatum;
import org.shumakriss.demo.data.Tasklist;
import org.shumakriss.demo.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping("/task")
public class TaskController {
    @Autowired
    MyRepository myRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ProcessServicesClient processClient;

    @Autowired
    QueryServicesClient queryClient;

    @Autowired
    UserTaskServicesClient taskClient;

    @RequestMapping(path="/{taskId}", method = RequestMethod.GET)
    @ResponseBody
    Task getTaskDetail(@PathVariable("taskId") Long taskId) {
        System.out.println("Get task detail for taskId=" + taskId);

        TaskInstance taskInstance = taskClient.findTaskById(taskId);
        String containerId = taskInstance.getContainerId();
        String processId = taskInstance.getProcessId();
        Task task = createTaskFromTaskInstance(taskInstance);

        List<TaskDatum> inData = new ArrayList<TaskDatum>();
        TaskInputsDefinition userTaskInputDefinitions = processClient.getUserTaskInputDefinitions(containerId, processId, taskInstance.getName());
        Map<String, Object> taskInputContentByTaskId = taskClient.getTaskInputContentByTaskId(containerId, taskId);
        for( String key: userTaskInputDefinitions.getTaskInputs().keySet()){
            TaskDatum taskDatum = new TaskDatum();
            taskDatum.setName(key);
            taskDatum.setType(userTaskInputDefinitions.getTaskInputs().get(key));

            if(taskInputContentByTaskId.containsKey(key))
                taskDatum.setValue(taskInputContentByTaskId.get(key));

            inData.add(taskDatum);
        }
        task.setInData(inData);

        List<TaskDatum> outData = new ArrayList<TaskDatum>();
        TaskOutputsDefinition userTaskOutputDefinitions = processClient.getUserTaskOutputDefinitions(containerId, processId, taskInstance.getName());
        Map<String, Object> taskOutputContentByTaskId = taskClient.getTaskOutputContentByTaskId(containerId, taskId);
        for( String key: userTaskOutputDefinitions.getTaskOutputs().keySet()){
            TaskDatum taskDatum = new TaskDatum();
            taskDatum.setName(key);
            taskDatum.setType(userTaskOutputDefinitions.getTaskOutputs().get(key));

            if(taskOutputContentByTaskId.containsKey(key))
                taskDatum.setValue(taskOutputContentByTaskId.get(key));

            outData.add(taskDatum);
        }
        task.setOutData(outData);

        task.setInputs(taskInputContentByTaskId);
        task.setOutputs(taskOutputContentByTaskId);
        task.setInputTypes(userTaskInputDefinitions.getTaskInputs());
        task.setOutputTypes(userTaskOutputDefinitions.getTaskOutputs());

        System.out.println(task);

        return task;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    Tasklist getTasks()  {
        System.out.println("Getting tasks");
        List<Task> tasks = new ArrayList<Task>();
        List<TaskSummary> taskSummaryList = taskClient.findTasks("kieserver", 0, 100);
        for(TaskSummary taskSummary : taskSummaryList)
            tasks.add(createTaskFromTaskSummary(taskSummary));
        Tasklist tasklist = new Tasklist();
        tasklist.setTasks(tasks);
        return tasklist;
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

}

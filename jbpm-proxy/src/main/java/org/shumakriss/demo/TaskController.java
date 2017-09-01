package org.shumakriss.demo;

import org.kie.server.api.model.definition.TaskInputsDefinition;
import org.kie.server.api.model.definition.TaskOutputsDefinition;
import org.kie.server.api.model.instance.TaskInstance;
import org.kie.server.api.model.instance.TaskSummary;
import org.kie.server.client.ProcessServicesClient;
import org.kie.server.client.QueryServicesClient;
import org.kie.server.client.UserTaskServicesClient;
import org.shumakriss.demo.data.Task;
import org.shumakriss.demo.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.List;

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
    Task getTaskDetail(@PathParam("containerId") String containerId, @PathParam("processId") String processId, @PathParam("taskId") Long taskId) {
        System.out.println("Getting task detail");

        TaskInstance taskInstance = taskClient.getTaskInstance("Evaluation", 1L);
        TaskInputsDefinition userTaskInputDefinitions = processClient.getUserTaskInputDefinitions(containerId, processId, taskInstance.getName());
        TaskOutputsDefinition userTaskOutputDefinitions = processClient.getUserTaskOutputDefinitions(containerId, processId, taskInstance.getName());

        Task task = createTaskFromTaskInstance(taskInstance);
        task.setInputs(userTaskInputDefinitions.getTaskInputs());
        task.setOutputs(userTaskOutputDefinitions.getTaskOutputs());

        return task;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    List<TaskSummary> getTasks()  {
        System.out.println("Getting tasks");
        return taskClient.findTasks("kieserver", 0, 100);
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

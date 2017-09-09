package org.shumakriss.demo;

import org.shumakriss.demo.data.Task;
import org.shumakriss.demo.data.Tasklist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@EnableAutoConfiguration
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @RequestMapping(path="/{taskId}", method = RequestMethod.GET)
    @ResponseBody
    Task getTaskDetail(@PathVariable("taskId") Long taskId) {
        System.out.println("Get task detail for taskId=" + taskId);
        return taskService.getTaskDetail(taskId);
    }

    @RequestMapping(path="/{taskId}", method = RequestMethod.PUT)
    @ResponseBody
    void updateProcessVariables(@PathVariable("taskId") Long taskId, @RequestBody Task task) {
        System.out.println("Update process variables for taskId=" + taskId + ", task=" + task);
        taskService.updateProcessVariables(taskId, task);
    }

    @RequestMapping(path="/{taskId}/states/completed", method = RequestMethod.PUT)
    @ResponseBody
    void completeTask(@PathVariable("taskId") Long taskId, @RequestBody Task task) {
        System.out.println("Complete taskId=" + taskId + ", task=" + task);
        taskService.completeTask(taskId, task);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    Tasklist getTasks(@PathParam("term") String term)  {
        if(term == null)
            System.out.println("Getting tasks");
        else
            System.out.println("Searching tasks");

       return taskService.getTasks(term);
    }



}

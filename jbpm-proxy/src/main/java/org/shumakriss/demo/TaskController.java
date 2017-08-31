package org.shumakriss.demo;

import org.kie.server.api.model.instance.ProcessInstance;
import org.kie.server.api.model.instance.TaskSummary;
import org.kie.server.client.ProcessServicesClient;
import org.kie.server.client.QueryServicesClient;
import org.kie.server.client.UserTaskServicesClient;
import org.shumakriss.demo.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
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

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    List<TaskSummary> getTasks()  {
        System.out.println("Getting tasks");
        List<TaskSummary> tasks = taskClient.findTasks("kieserver", 0, 100);
        return tasks;
    }

}

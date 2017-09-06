package org.shumakriss.demo;

import org.kie.internal.process.CorrelationKey;
import org.kie.internal.process.CorrelationKeyFactory;
import org.kie.internal.process.CorrelationProperty;
import org.kie.server.api.model.instance.ProcessInstance;
import org.kie.server.client.ProcessServicesClient;
import org.kie.server.client.QueryServicesClient;
import org.shumakriss.demo.data.MyCorrelationKey;
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
@RequestMapping("/process/instances")
public class ProcessInstanceController {
    @Autowired
    MyRepository myRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ProcessServicesClient processClient;

    @Autowired
    QueryServicesClient queryClient;

    @RequestMapping(method=RequestMethod.GET)
    @ResponseBody
    List<ProcessInstance> listInstances()  {
        List<Integer> statuses = new ArrayList<Integer>();
        statuses.add(0);
        statuses.add(1);
        statuses.add(2);
        statuses.add(3);
        statuses.add(4);
        statuses.add(5);
        List<ProcessInstance> processInstances = queryClient.findProcessInstances(0, 100);

        for(ProcessInstance instance : processInstances){
            Map<String, Object> variables = processClient.getProcessInstanceVariables(instance.getContainerId(), instance.getId());
            instance.setVariables(variables);
        }

        return processInstances;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    void startInstance(@PathParam("containerId") String containerId, @PathParam("processId") String processId, @PathParam("trackingNumber") String trackingNumber, @RequestBody Map<String, Object> parameters)  {
        System.out.println("Starting process");

        if(trackingNumber == null)
            processClient.startProcess(containerId, processId, parameters);
        else {
            CorrelationKey key = new MyCorrelationKey(trackingNumber);
            processClient.startProcess(containerId, processId, key, parameters);
        }
    }

}

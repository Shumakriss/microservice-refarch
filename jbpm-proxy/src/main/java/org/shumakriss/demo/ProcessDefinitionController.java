package org.shumakriss.demo;

import org.kie.server.api.model.definition.ProcessDefinition;
import org.kie.server.api.model.instance.ProcessInstance;
import org.kie.server.client.ProcessServicesClient;
import org.kie.server.client.QueryServicesClient;
import org.shumakriss.demo.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Chris on 9/4/16.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/process/definitions")
public class ProcessDefinitionController {

    @Autowired
    QueryServicesClient queryClient;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    List<ProcessDefinition> definitions()  {
        List<ProcessDefinition> processes = queryClient.findProcesses(0, 100);
        return processes;
    }

}

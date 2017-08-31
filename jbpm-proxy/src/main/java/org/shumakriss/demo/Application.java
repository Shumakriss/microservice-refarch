package org.shumakriss.demo;

import org.kie.server.api.marshalling.MarshallingFormat;
import org.kie.server.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Chris on 9/4/16.
 */

@SpringBootApplication
public class Application {

    @Value("${KIE_SERVER_URL:http://localhost:8081/kie-server/services/rest/server}")
    private String kieServerUrl;

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Bean
    public KieServicesConfiguration kieServicesConfiguration() {
        System.out.println("Using kieserverurl=" + kieServerUrl);
        KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(kieServerUrl, "kieserver", "kieserver1!");
        config.setMarshallingFormat(MarshallingFormat.JSON);
        return config;
    }

    @Bean
    public KieServicesClient kieServicesClient(KieServicesConfiguration kieServicesConfiguration) {
        return KieServicesFactory.newKieServicesClient(kieServicesConfiguration);
    }

    @Bean
    public ProcessServicesClient processServicesClient(KieServicesClient kieServicesClient) {
        return kieServicesClient.getServicesClient(ProcessServicesClient.class);
    }

    @Bean
    public UserTaskServicesClient userTaskServicesClient(KieServicesClient kieServicesClient) {
        return kieServicesClient.getServicesClient(UserTaskServicesClient.class);
    }

    @Bean
    public QueryServicesClient queryServicesClient(KieServicesClient kieServicesClient) {
        return kieServicesClient.getServicesClient(QueryServicesClient.class);
    }
}

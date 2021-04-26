package ch.fhnw.digibp.app;

import ch.fhnw.digibp.model.Candidate;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class Helper {

    @Autowired
    ExternalTaskClient client;

    @PostConstruct
    private void subscribeTopics() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        RestTemplate restTemplate = new RestTemplate();

        client.subscribe("GetJobOffers")
//                .tenantIdIn("showcase")
                .handler((ExternalTask externalTask, ExternalTaskService externalTaskService) -> {
                    try {

                        List<String> jobOfferIds = Objects.requireNonNull(restTemplate.exchange("https://hook.integromat.com/2yni7gbntflnphfxn5af8uu1k6qyoaqq", HttpMethod.GET, request, new ParameterizedTypeReference<List<Candidate>>() {
                        }).getBody()).stream().map(Candidate::getJobDescriptionId).distinct().collect(Collectors.toList());

                        Map<String, Object> variables = new HashMap<>();
                        variables.put("jobOfferIds", jobOfferIds);

                        externalTaskService.complete(externalTask, variables);
                    } catch (Exception e) {
                        externalTaskService.handleBpmnError(externalTask, "failed");
                    }
                })
                .open();
    }
}

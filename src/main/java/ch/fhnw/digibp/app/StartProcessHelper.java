package ch.fhnw.digibp.app;

import ch.fhnw.digibp.model.Candidate;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class StartProcessHelper implements JavaDelegate{

    @Value("${camunda.rest.url}")
    private String camundaRestUrl;

    @Override
    public void execute(DelegateExecution delegateExecution) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        RestTemplate restTemplate = new RestTemplate();

        List<Candidate> candidates = restTemplate.exchange("https://hook.integromat.com/q2afwk840ajotdamwsfpoujg8beaa751", HttpMethod.GET, request, new ParameterizedTypeReference<List<Candidate>>(){}).getBody();

        HttpEntity<String> secondRequest = new HttpEntity<String>("", headers);

        for (Candidate candidate : candidates) {
            if (candidate.getJobDescriptionId().equals(delegateExecution.getVariable("selectedJob"))) {
                //TODO Hier müssen die Daten vom Candidate dem Prozess mitgegeben werden
                restTemplate.postForObject(camundaRestUrl + "/process-definition/key/04_selection-phase/start", secondRequest, String.class);
            }
        }
    }
}

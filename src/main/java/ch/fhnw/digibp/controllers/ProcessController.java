package ch.fhnw.digibp.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequestMapping("/process")
@Component
public class ProcessController implements JavaDelegate{

    @Value("${camunda.rest.url}")
    private String camundaRestUrl;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        RestTemplate restTemplate = new RestTemplate();

        List<Candidate> candidates = restTemplate.exchange("https://hook.integromat.com/2yni7gbntflnphfxn5af8uu1k6qyoaqq", HttpMethod.GET, request, new ParameterizedTypeReference<List<Candidate>>(){}).getBody();

        //TODO change "test" im Link zu spezifischer process definition
        candidates.stream().filter(candidate -> candidate.getJobDescriptionId().equals(delegateExecution.getVariable("selectedJob"))).forEach(candidate -> restTemplate.postForObject(camundaRestUrl+"/process-definition/key/selection-phase/start", request, String.class));
    }
    //TO-DO implement exception handling
}

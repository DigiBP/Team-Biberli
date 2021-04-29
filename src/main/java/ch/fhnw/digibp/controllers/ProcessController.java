package ch.fhnw.digibp.controllers;

import ch.fhnw.digibp.model.Candidate;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/process")
public class ProcessController implements JavaDelegate{

    @GetMapping("/{processId}")
    public String startProcessForCandidates(@PathVariable String processId ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        RestTemplate restTemplate = new RestTemplate();

        List<Candidate> candidates = restTemplate.exchange("https://hook.integromat.com/2yni7gbntflnphfxn5af8uu1k6qyoaqq", HttpMethod.GET, request, new ParameterizedTypeReference<List<Candidate>>(){}).getBody();

        candidates.stream().filter(candidate -> candidate.getJobDescriptionId().equals(processId)).forEach(candidate -> restTemplate.postForObject("https://digibp-biberli.herokuapp.com/engine-rest/process-definition/key/Process_1cfjfj0/start", request, String.class));

        return "done";
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        RestTemplate restTemplate = new RestTemplate();

        List<Candidate> candidates = restTemplate.exchange("https://hook.integromat.com/2yni7gbntflnphfxn5af8uu1k6qyoaqq", HttpMethod.GET, request, new ParameterizedTypeReference<List<Candidate>>(){}).getBody();

        candidates.stream().filter(candidate -> candidate.getJobDescriptionId().equals(delegateExecution.getVariable("selectedJob"))).forEach(candidate -> restTemplate.postForObject("https://digibp-biberli.herokuapp.com/engine-rest/process-definition/key/Process_1cfjfj0/start", request, String.class));
    }
}

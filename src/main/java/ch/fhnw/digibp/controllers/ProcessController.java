package ch.fhnw.digibp.controllers;

import ch.fhnw.digibp.model.Candidate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/process")
public class ProcessController {

    @GetMapping
    public String startProcessForCandidates(@RequestBody List<Candidate> candidates) {
        //TODO here comes an Array with candidates, foreach canditate start a new process
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        RestTemplate restTemplate = new RestTemplate();

        candidates.forEach(candidate -> restTemplate.postForObject("https://digibp-biberli.herokuapp.com/engine-rest/process-definition/key/Process_1cfjfj0/start", request, String.class));

        return "done";
    }

}

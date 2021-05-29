package ch.fhnw.digibp.service;

import ch.fhnw.digibp.model.Job;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class JobService {

    public List<Job> getJobs() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange("https://hook.integromat.com/kvy3mtnfoqrq05glddboc8hpeckjxssh", HttpMethod.GET, request, new ParameterizedTypeReference<List<Job>>() {
        }).getBody();
    }
}

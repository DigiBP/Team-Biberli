package ch.fhnw.digibp.service;

import ch.fhnw.digibp.model.Candidate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CandidateService {

    @Value("${camunda.rest.url}")
    private String camundaRestUrl;

    public List<Candidate> getCandidates()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange("https://hook.integromat.com/q2afwk840ajotdamwsfpoujg8beaa751", HttpMethod.GET, request, new ParameterizedTypeReference<List<Candidate>>() {
        }).getBody();
    }

    public void startProcessForCandidate(String json) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);
        HttpEntity<JsonNode> request2 = new HttpEntity<>(rootNode, headers);
        restTemplate.postForObject(camundaRestUrl + "/process-definition/key/recruting-process/start", request2, String.class);
    }

}

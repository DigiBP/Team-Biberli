package ch.fhnw.digibp.app;

import ch.fhnw.digibp.model.Candidate;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class Helper implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        RestTemplate restTemplate = new RestTemplate();

        List<String> jobOfferIds = Objects.requireNonNull(restTemplate.exchange("https://hook.integromat.com/2yni7gbntflnphfxn5af8uu1k6qyoaqq", HttpMethod.GET, request, new ParameterizedTypeReference<List<Candidate>>() {
        }).getBody()).stream().map(Candidate::getJobDescriptionId).distinct().collect(Collectors.toList());

        delegateExecution.setVariable("availableJobsToStart", jobOfferIds.toString());

        //TODO set options for enum jobOfferIds
//        Map<String, String> values = new LinkedHashMap<String, String>();
//        jobOfferIds.forEach(s -> values.put(s, s));
//        EnumFormType formType = new EnumFormType(values);
//        delegateExecution.setVariable("jobOfferIds", formType);
    }
}

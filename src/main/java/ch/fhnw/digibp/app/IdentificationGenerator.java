package ch.fhnw.digibp.app;

import ch.fhnw.digibp.model.Job;
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
import java.util.OptionalInt;

@Component
public class IdentificationGenerator implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        RestTemplate restTemplate = new RestTemplate();

        OptionalInt highestId = Objects.requireNonNull(restTemplate.exchange("https://hook.integromat.com/kvy3mtnfoqrq05glddboc8hpeckjxssh", HttpMethod.GET, request, new ParameterizedTypeReference<List<Job>>() {
        }).getBody()).stream().map(Job::getJobId).filter(Objects::nonNull).filter(s -> !s.equalsIgnoreCase("null")).mapToInt(s -> Integer.parseInt(s)).sorted().findFirst();

        int id=0;
        if (highestId.isPresent()){
            id=highestId.getAsInt()+1;
        }
        delegateExecution.setVariable("jobId", ""+id);
    }
}

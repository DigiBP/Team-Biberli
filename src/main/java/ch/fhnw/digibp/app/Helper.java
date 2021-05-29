package ch.fhnw.digibp.app;

import ch.fhnw.digibp.model.Candidate;
import ch.fhnw.digibp.model.Job;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.Spin;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        List<String> jobOfferIds = Objects.requireNonNull(restTemplate.exchange("https://hook.integromat.com/q2afwk840ajotdamwsfpoujg8beaa751", HttpMethod.GET, request, new ParameterizedTypeReference<List<Candidate>>() {
        }).getBody()).stream().map(Candidate::getJobDescriptionId).distinct().collect(Collectors.toList());

        List<Job> jobs = Objects.requireNonNull(restTemplate.exchange("https://hook.integromat.com/kvy3mtnfoqrq05glddboc8hpeckjxssh", HttpMethod.GET, request, new ParameterizedTypeReference<List<Job>>() {
        }).getBody()).stream().filter(Objects::nonNull).filter(job -> jobOfferIds.contains(job.getJobId())).collect(Collectors.toList());

        Map<String, String> jobsMap = new HashMap<>();
        jobs.forEach(s -> jobsMap.put(s.getJobId(), ""+s.getJobId() +" - "+ s.getJobTitle()));

        delegateExecution.setVariable("AVAILABLE_JOB_ID",  Spin.JSON(jobsMap));
    }
}

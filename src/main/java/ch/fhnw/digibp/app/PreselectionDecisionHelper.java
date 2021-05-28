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
import java.util.Map;
import java.util.Objects;

@Component
public class PreselectionDecisionHelper implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List dmnResultList = (List) delegateExecution.getVariable("dmnResult");
        Map<String, String> dmnResultMap = (Map<String, String>) dmnResultList.get(0);
        String grade = dmnResultMap.get("decision_preselection");

        if (Integer.parseInt(grade) >= getNeededGrade((String) delegateExecution.getVariable("JobDescriptionId"))) {
            delegateExecution.setVariable("decision", "yes");
        } else {
            delegateExecution.setVariable("decision", "no");
        }
    }

    private int getNeededGrade(String jobDescriptionId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        RestTemplate restTemplate = new RestTemplate();

        String neededGrade = Objects.requireNonNull(restTemplate.exchange("https://hook.integromat.com/kvy3mtnfoqrq05glddboc8hpeckjxssh", HttpMethod.GET, request, new ParameterizedTypeReference<List<Job>>() {
        }).getBody()).stream().filter(job -> job.getJobId().equals(jobDescriptionId)).filter(job -> job.getGrade() != null).findFirst().get().getGrade();

        neededGrade = neededGrade.substring(neededGrade.length() - 1);
        return Integer.parseInt(neededGrade);
    }
}

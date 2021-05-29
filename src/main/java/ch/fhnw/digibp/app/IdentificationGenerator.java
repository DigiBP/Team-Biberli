package ch.fhnw.digibp.app;

import ch.fhnw.digibp.model.Job;
import ch.fhnw.digibp.service.JobService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Component
public class IdentificationGenerator implements JavaDelegate {

    @Autowired
    JobService jobService;

    @Override
    public void execute(DelegateExecution delegateExecution) {

        Optional<Integer> highestId = Objects.requireNonNull(jobService.getJobs()).stream().map(Job::getJobId).filter(Objects::nonNull).filter(s -> !s.equalsIgnoreCase("null")).map(s -> Integer.parseInt(s)).sorted(Collections.reverseOrder()).findFirst();

        int id=0;
        if (highestId.isPresent()){
            id=highestId.get()+1;
        }
        delegateExecution.setVariable("jobId", ""+id);
    }
}

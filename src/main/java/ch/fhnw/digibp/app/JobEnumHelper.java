package ch.fhnw.digibp.app;

import ch.fhnw.digibp.model.Candidate;
import ch.fhnw.digibp.model.Job;
import ch.fhnw.digibp.service.CandidateService;
import ch.fhnw.digibp.service.JobService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.Spin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class JobEnumHelper implements JavaDelegate {

    @Autowired
    JobService jobService;
    @Autowired
    CandidateService candidateService;

    @Override
    public void execute(DelegateExecution delegateExecution) {

        List<String> jobOfferIds = Objects.requireNonNull(candidateService.getCandidates()).stream().map(Candidate::getJobDescriptionId).distinct().collect(Collectors.toList());

        List<Job> jobs = Objects.requireNonNull(jobService.getJobs()).stream().filter(Objects::nonNull).filter(job -> jobOfferIds.contains(job.getJobId())).collect(Collectors.toList());

        Map<String, String> jobsMap = new HashMap<>();
        jobs.forEach(s -> jobsMap.put(s.getJobId(), ""+s.getJobId() +" - "+ s.getJobTitle()));

        delegateExecution.setVariable("AVAILABLE_JOB_ID",  Spin.JSON(jobsMap));
    }
}

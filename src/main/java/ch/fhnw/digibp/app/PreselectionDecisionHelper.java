package ch.fhnw.digibp.app;

import ch.fhnw.digibp.model.Job;
import ch.fhnw.digibp.service.JobService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class PreselectionDecisionHelper implements JavaDelegate {

    @Autowired
    JobService jobService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List dmnResultList = (List) delegateExecution.getVariable("dmnResult");
        Map<String, String> dmnResultMap = (Map<String, String>) dmnResultList.get(0);
        String grade = dmnResultMap.get("decision_preselection");

        if (Integer.parseInt(grade) >= getNeededGrade((String) delegateExecution.getVariable("JobDescriptionId"), delegateExecution)) {
            delegateExecution.setVariable("decision", "yes");
        } else {
            delegateExecution.setVariable("decision", "no");
        }
    }

    private int getNeededGrade(String jobDescriptionId, DelegateExecution d) {
        Job job1 = Objects.requireNonNull(jobService.getJobs()).stream().filter(job -> job.getJobId().equals(jobDescriptionId)).filter(job -> job.getGrade() != null).findFirst().get();

        setProcessVariableOnTheFly(d, job1);
        String neededGrade = job1.getGrade();

        if (Character.isDigit(neededGrade.charAt(neededGrade.length() - 2))) {
            neededGrade = neededGrade.substring(neededGrade.length() - 2);
        } else {
            neededGrade = neededGrade.substring(neededGrade.length() - 1);
        }
        return Integer.parseInt(neededGrade);
    }

    private void setProcessVariableOnTheFly(DelegateExecution d, Job job1) {
        d.setVariable("Tasks", job1.getTasks());
        d.setVariable("Salary", job1.getSalary());
        d.setVariable("Experience", job1.getExperience());
        d.setVariable("Supervisor", job1.getSupervisor());
        d.setVariable("Grade", job1.getGrade());
        d.setVariable("JobTitle", job1.getJobTitle());
        d.setVariable("JobId", job1.getJobId());
    }
}

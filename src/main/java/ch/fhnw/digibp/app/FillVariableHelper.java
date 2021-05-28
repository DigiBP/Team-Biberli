package ch.fhnw.digibp.app;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class FillVariableHelper implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println(delegateExecution.getVariable("Name"));
        System.out.println(delegateExecution.getVariable("Surname"));
        System.out.println(delegateExecution.getVariable("working_years"));
        System.out.println(delegateExecution.getVariable("highest_diploma"));
        System.out.println(delegateExecution.getVariable("avarage_grading"));
        System.out.println(delegateExecution.getVariable("MobileNumber"));
        System.out.println(delegateExecution.getVariable("JobDescriptionId"));
    }
}

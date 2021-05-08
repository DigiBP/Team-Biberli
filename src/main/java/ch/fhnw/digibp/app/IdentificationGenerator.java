package ch.fhnw.digibp.app;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class IdentificationGenerator implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        int id = ThreadLocalRandom.current().nextInt();
        delegateExecution.setVariable("jobId", ""+id);

        //TODO get last Id from Excel and increment it
    }
}

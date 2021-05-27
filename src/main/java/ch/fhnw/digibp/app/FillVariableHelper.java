package ch.fhnw.digibp.app;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class FillVariableHelper implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        //TODO Get User from Process

        //TODO From User jobId get right job from Job Excel

        //TODO set Variable min Grade from Job / Falls beim Start vom Prozess keine Variablen mitgegeben werden können müssen diese hier geholt und gesetzt werden -> Highest Diploma from Candidate / Average Grade from Candidate / Working Years from Candidate


    }
}

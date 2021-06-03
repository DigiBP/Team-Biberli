package ch.fhnw.digibp.app;

import ch.fhnw.digibp.service.TranslationService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TranslationHelper implements JavaDelegate {

    @Autowired
    TranslationService translationService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String taskDescription = (String) delegateExecution.getVariable("tasks");

        delegateExecution.setVariable("tasks_de", translationService.getTranslation("DE", taskDescription));
        delegateExecution.setVariable("tasks_fr", translationService.getTranslation("FR", taskDescription));
        delegateExecution.setVariable("tasks_it", translationService.getTranslation("IT", taskDescription));
    }
}

package ch.fhnw.digibp.app;

import ch.fhnw.digibp.model.Candidate;
import ch.fhnw.digibp.service.CandidateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartProcessHelper implements JavaDelegate {

    @Autowired
    private CandidateService candidateService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws JsonProcessingException {

        List<Candidate> candidates = candidateService.getCandidates();

        for (Candidate candidate : candidates) {
            if (candidate.getJobDescriptionId().equals(delegateExecution.getVariable("selectedJob"))) {
                String json = getJson(candidate);
                candidateService.startProcessForCandidate(json);
            }
        }
    }



    public String getJson(Candidate candidate) {
        return "{\n" +
                "  \"variables\": {" +
                "    \"Name\" : {" +
                "        \"value\" : \"" + candidate.getName() + "\"," +
                "        \"type\": \"String\"" +
                "    }," +
                "    \"Surname\" : {" +
                "      \"value\" : \"" + candidate.getSurname() + "\"," +
                "      \"type\": \"String\"" +
                "    }," +
                "   \"JobDescriptionId\" : {" +
                "       \"value\" : \"" + candidate.getJobDescriptionId() + "\"," +
                "       \"type\": \"String\"" +
                "    }," +
                "   \"avarage_grading\" : {" +
                "       \"value\" : \"" + candidate.getAverageGrade() + "\"," +
                "       \"type\": \"String\"" +
                "    }," +
                "   \"highest_diploma\" : {" +
                "       \"value\" : \"" + candidate.getHighestDiploma() + "\"," +
                "       \"type\": \"String\"" +
                "    }," +
                "    \"Mail\" : {" +
                "         \"value\" : \"" + candidate.getMail() + "\"," +
                "          \"type\": \"String\"" +
                "    }," +
                "    \"Address\" : {" +
                "         \"value\" : \"" + candidate.getAddress() + "\"," +
                "          \"type\": \"String\"" +
                "    }," +
                "    \"MobileNumber\" : {" +
                "       \"value\" : \"" + candidate.getMobileNumber() + "\"," +
                "       \"type\": \"String\"" +
                "   }," +
                "    \"working_years\" : {" +
                "       \"value\" : \"" + candidate.getWorkingYears() + "\"," +
                "       \"type\": \"String\"" +
                "   }}}";
    }
}

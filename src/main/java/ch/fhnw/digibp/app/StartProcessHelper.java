package ch.fhnw.digibp.app;

import ch.fhnw.digibp.model.Candidate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class StartProcessHelper implements JavaDelegate {

    @Value("${camunda.rest.url}")
    private String camundaRestUrl;

    @Override
    public void execute(DelegateExecution delegateExecution) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<String>("", headers);
        RestTemplate restTemplate = new RestTemplate();

        List<Candidate> candidates = restTemplate.exchange("https://hook.integromat.com/q2afwk840ajotdamwsfpoujg8beaa751", HttpMethod.GET, request, new ParameterizedTypeReference<List<Candidate>>() {
        }).getBody();

        for (Candidate candidate : candidates) {
            if (candidate.getJobDescriptionId().equals(delegateExecution.getVariable("selectedJob"))) {
                String json = getJson(candidate);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(json);
                HttpEntity<JsonNode> request2 = new HttpEntity<>(rootNode, headers);
                restTemplate.postForObject(camundaRestUrl + "/process-definition/key/recruting-process/start", request2, String.class);
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

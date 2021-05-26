package ch.fhnw.digibp.app;

import org.apache.commons.codec.binary.Base64;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;


@Component
public class MailingHelper implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {

        HttpHeaders headers = createHeaders("api", "e5f4c78f466b826a709a47f3b52793a6-fa6e84b7-aaf08b9e");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("from", "XYZ Inc. <stephan@XYZ.inc>");
        map.add("to", "simon.hafner@students.fhnw.ch");
        map.add("subject", "hello");
        map.add("text", "testing");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity( "https://api.mailgun.net/v3/" + "mailgun.simonhafner.me" + "/messages", request , String.class );
    }

    HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }
}

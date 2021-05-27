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

import camundajar.impl.scala.Console;

import java.nio.charset.Charset;


@Component
public class MailingHelper implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {

        String recipient = delegateExecution.getVariable("recipient").toString();
        String text = delegateExecution.getVariable("text").toString();
        String subject = delegateExecution.getVariable("subject").toString();
        
        HttpHeaders headers = createHeaders("api", "bb9954d61cdacf670a6d03535cf7ec96-fa6e84b7-7fa8398b");
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("from", "XYZ Inc. <HR@mailgun.simonhafner.me>");
        map.add("to", recipient);
        map.add("subject", subject);
        map.add("text", text);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity("https://api.mailgun.net/v3/mailgun.simonhafner.me/messages", request , String.class);
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

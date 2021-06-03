package ch.fhnw.digibp.service;

import ch.fhnw.digibp.model.Root;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TranslationService {

    public String getTranslation(String targetLanguage, String text){
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request2 = new HttpEntity<>("", headers);
        Root root = restTemplate.postForObject("https://api-free.deepl.com/v2/translate?auth_key=493c1bf3-50ec-0130-0b2a-fde22e5982b7:fx&text="+text+"&target_lang="+targetLanguage, request2, Root.class);
        return root.getTranslations().get(0).getText();
    }

}

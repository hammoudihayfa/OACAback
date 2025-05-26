package tn.esprit.userservice.services;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SmsService {
    public void sendSms(String phoneNumber, String message) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String smsApiUrl = "https://textbelt.com/text";
            Map<String, Object> payload = new HashMap<>();
            payload.put("phone", phoneNumber);
            payload.put("message", message);
            payload.put("key", "textbelt");

            ResponseEntity<String> response = restTemplate.postForEntity(smsApiUrl, payload, String.class);
            System.out.println("SMS Response: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Erreur d'envoi SMS : " + e.getMessage());
        }
    }
}

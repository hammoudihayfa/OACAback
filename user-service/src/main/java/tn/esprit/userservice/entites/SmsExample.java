package tn.esprit.userservice.entites;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class SmsExample {
    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = "https://textbelt.com/text";
            HttpPost postRequest = new HttpPost(url);

            String phoneNumber = "+21624903943";  // Numéro du destinataire
            String message = "Hello, ceci est un message de test depuis Textbelt!";
            String apiKey = "43fbc420aa1cf5e28a59654484a6f5f13b45e4aeglj0Vg8HPawJSVXEppy6Oi8ZG";  // Ta clé API Textbelt

            String jsonBody = String.format(
                    "{\"phone\": \"%s\", \"message\": \"%s\", \"key\": \"%s\"}",
                    phoneNumber, message, apiKey
            );

            StringEntity entity = new StringEntity(jsonBody);
            postRequest.setEntity(entity);
            postRequest.setHeader("Content-type", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                HttpEntity responseEntity = response.getEntity();
                String responseBody = EntityUtils.toString(responseEntity);

                System.out.println("Response Code: " + response.getCode());
                System.out.println("Response: " + responseBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

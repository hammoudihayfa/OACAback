package tn.esprit.agentservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AgentService agentService;

    public void notifyAgentForFailure(Long agentId) {
        // Récupère l'email de l'agent via AgentService
        String agentEmail = agentService.getAgentEmail(agentId);
        String subject = "Alerte Panne Equipement";
        String text = "Bonjour, un de vos équipements est tombé en panne.";

        // Appel à l'email-service pour envoyer l'email
        String emailServiceUrl = "http://localhost:8089/send-email";
        restTemplate.postForObject(emailServiceUrl, new EmailRequest(agentEmail, subject, text), Void.class);
    }
}

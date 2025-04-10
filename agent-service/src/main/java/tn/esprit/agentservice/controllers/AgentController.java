package tn.esprit.agentservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.agentservice.services.NotificationService;

@RestController
@RequestMapping("/notification")
public class AgentController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/simulate-failure/{agentId}")
    public ResponseEntity<Void> simulateFailure(@PathVariable Long agentId) {
        notificationService.notifyAgentForFailure(agentId);
        return ResponseEntity.ok().build();
    }
}

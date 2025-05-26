package tn.esprit.agentservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.agentservice.services.NotificationService;

@RestController
@CrossOrigin(origins = "*")

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

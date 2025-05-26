package tn.esprit.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import tn.esprit.userservice.DTO.ResetPasswordRequest;
import tn.esprit.userservice.entites.User;
import tn.esprit.userservice.services.SmsService;
import tn.esprit.userservice.services.UserService;
import tn.esprit.userservice.DTO.EmailRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

 private final UserService userService;
 private final RestTemplate restTemplate;
 @Autowired
 private SmsService smsService;
 private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();



 @Autowired
 public UserController(UserService userService, RestTemplate restTemplate) {
  this.userService = userService;
  this.restTemplate = restTemplate;
 }

 @PostMapping("/signup")
 public ResponseEntity<User> signup(@RequestBody User user) {
  try {
   User registeredUser = userService.register(user);
   sendAdminNotification(user);

   sendNotificationToClients("Nouvel utilisateur : " + user.getName());

   return ResponseEntity.ok(registeredUser);
  } catch (DataIntegrityViolationException e) {
   return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
  } catch (Exception e) {
   e.printStackTrace();
   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
  }
 }

 private void sendAdminNotification(User user) {
  try {
   EmailRequest emailRequest = new EmailRequest();
   emailRequest.setTo("hammoudi.hayfa@esprit.tn");
   emailRequest.setSubject("Nouvel utilisateur inscrit");
   emailRequest.setText("Un nouvel utilisateur a créé un compte avec succès. Détails de l'utilisateur :\n" +
           "Nom : " + user.getName() + "\n" +
           "Email : " + user.getEmail() + "\n" +
           "Role : " + user.getRole());

   String emailServiceUrl = "http://localhost:8089/send-email";
   restTemplate.postForEntity(emailServiceUrl, emailRequest, Void.class);
  } catch (Exception e) {
   e.printStackTrace();
   System.out.println("Erreur lors de l'envoi de la notification à l'admin.");
  }
 }

 @PostMapping("/login")
 public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
  String email = body.get("email");
  String password = body.get("password");
  User loggedInUser = userService.login(email, password);

  if (loggedInUser != null) {
   Map<String, String> response = new HashMap<>();
   response.put("message", "Connexion réussie");
   response.put("role", loggedInUser.getRole());
   return ResponseEntity.ok(response);
  } else {
   return ResponseEntity.status(401).body("Email ou mot de passe incorrect");
  }
 }
 @GetMapping("/notifications/stream")
 public SseEmitter streamNotifications() {
  SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
  emitters.add(emitter);

  emitter.onCompletion(() -> emitters.remove(emitter));
  emitter.onTimeout(() -> emitters.remove(emitter));

  return emitter;
 }

 private void sendNotificationToClients(String message) {
  for (SseEmitter emitter : emitters) {
   try {
    emitter.send(SseEmitter.event()
            .name("notification")
            .data(message));
   } catch (IOException e) {
    emitters.remove(emitter);
   }
  }
 }
 @GetMapping("/{id}")
 public ResponseEntity<User> getUser(@PathVariable Long id) {
  Optional<User> userOptional = userService.getUserById(id);
  if (userOptional.isPresent()) {
   return ResponseEntity.ok(userOptional.get());
  } else {
   return ResponseEntity.notFound().build();
  }
 }

 @PutMapping("/{id}")
 public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
  User updated = userService.updateUser(id, updatedUser);
  if (updated != null) {
   return ResponseEntity.ok(updated);
  } else {
   return ResponseEntity.notFound().build();
  }
 }
 @PostMapping("/forgot-password")
 public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
  String phoneNumber = request.get("phoneNumber");

  Optional<User> userOptional = userService.findByPhoneNumber(phoneNumber);

  if (userOptional.isEmpty()) {
   return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Numéro de téléphone introuvable");
  }


  String resetCode = String.valueOf((int)(Math.random() * 900000) + 100000);

  String message = "Votre code de réinitialisation est : " + resetCode;

  try {
   smsService.sendSms(phoneNumber, message);
   return ResponseEntity.ok(Map.of("message", "Code envoyé avec succès", "code", resetCode));
  } catch (Exception e) {
   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
           .body("Erreur lors de l'envoi du SMS : " + e.getMessage());
  }
 }
 @PostMapping("/reset-password")
 public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
  User user = userService.resetPassword(request.getPhoneNumber(), request.getNewPassword(), request.getEnteredCode());

  if (user == null) {
   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Code incorrect ou utilisateur non trouvé");
  }

  return ResponseEntity.status(HttpStatus.OK).body("Mot de passe réinitialisé avec succès");
 }
}

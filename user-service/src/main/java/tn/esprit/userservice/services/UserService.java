package tn.esprit.userservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.userservice.entites.User;

import tn.esprit.userservice.repository.UserRepo;

import java.util.Optional;

@Service
public class UserService {

 private final UserRepo userRepository;


 private final BCryptPasswordEncoder passwordEncoder;

 @Autowired
 public UserService(UserRepo userRepository,  BCryptPasswordEncoder passwordEncoder) {
  this.userRepository = userRepository;
  this.passwordEncoder = passwordEncoder;
 }

 public User register(User user) {
  System.out.println("Inscription utilisateur : " + user.getEmail());
  user.setPassword(passwordEncoder.encode(user.getPassword()));
  return userRepository.save(user);
 }

 public User login(String email, String password) {
  return userRepository.findByEmail(email)
          .filter(u -> passwordEncoder.matches(password, u.getPassword()))
          .orElse(null);
 }
 public Optional<User> getUserById(Long id) {
  return userRepository.findById(id);
 }

 public User updateUser(Long id, User updatedUser) {
  Optional<User> existingUserOptional = userRepository.findById(id);
  if (existingUserOptional.isPresent()) {
   User user = existingUserOptional.get();
   user.setName(updatedUser.getName());
   user.setPrenom(updatedUser.getPrenom());
   user.setEmail(updatedUser.getEmail());
   user.setAeroport(updatedUser.getAeroport());
   user.setRole(updatedUser.getRole());
   user.setPassword(updatedUser.getPassword());
   return userRepository.save(user);
  }
  return null;
 }

 public Optional<User> findUserByEmail(String email) {
  return userRepository.findByEmail(email);
 }

 public Optional<User> findByPhoneNumber(String phoneNumber) {
  return userRepository.findByPhoneNumber(phoneNumber);
 }


 public User resetPassword(String phoneNumber, String newPassword, String enteredCode) {
  Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);

  if (userOptional.isEmpty()) {
   return null;
  }

  User user = userOptional.get();

  if (user.getResetCode() != null && user.getResetCode().equals(enteredCode)) {
   user.setPassword(newPassword);
   user.setResetCode(null);
   return userRepository.save(user);
  }

  return null;
 }
 public User save(User user) {
  return userRepository.save(user);
 }


}

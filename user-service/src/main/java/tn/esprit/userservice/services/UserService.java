package tn.esprit.userservice.services;

import tn.esprit.userservice.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.userservice.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUser{
    @Autowired
    private UserRepo userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateUser(Long id, User user) {
        User existing = getUserById(id);
        existing.setNom(user.getNom());
        existing.setPrenom(user.getPrenom());
        existing.setEmail(user.getEmail());
        existing.setMotDePasse(user.getMotDePasse());
        existing.setRole(user.getRole());
        return userRepository.save(existing);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getMotDePasse().equals(password))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }

    @Override
    public String forgotPassword(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) throw new RuntimeException("User not found");
        return "Mot de passe actuel: " + userOpt.get().getMotDePasse();
    }

    @Override
    public User resetPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        user.setMotDePasse(newPassword);
        return userRepository.save(user);
    }
}

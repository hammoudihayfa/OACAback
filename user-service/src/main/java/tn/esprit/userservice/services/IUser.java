package tn.esprit.userservice.services;

import tn.esprit.userservice.entites.User;

import java.util.List;

public interface IUser {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User login(String email, String password);
    String forgotPassword(String email);
    User resetPassword(String email, String newPassword);
}

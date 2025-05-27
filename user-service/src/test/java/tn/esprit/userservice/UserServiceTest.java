package tn.esprit.userservice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tn.esprit.userservice.entites.User;
import tn.esprit.userservice.repository.UserRepo;
import tn.esprit.userservice.services.UserService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepo userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setIdUser(1L);
        user.setEmail("test@example.com");
        user.setPassword("plainPassword");
        user.setPhoneNumber("1234567890");
        user.setResetCode("reset123");
        user.setName("John");
        user.setPrenom("Doe");
        user.setAeroport("CDG");
        user.setRole("USER");
    }

    @Test
    void testRegister_encodesPasswordAndSaves() {
        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User savedUser = userService.register(user);

        assertEquals("encodedPassword", savedUser.getPassword());
        verify(passwordEncoder, times(1)).encode("plainPassword");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testLogin_successful() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("plainPassword", user.getPassword())).thenReturn(true);

        User loggedInUser = userService.login("test@example.com", "plainPassword");

        assertNotNull(loggedInUser);
        assertEquals("test@example.com", loggedInUser.getEmail());
    }

    @Test
    void testLogin_failWrongPassword() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrongPassword", user.getPassword())).thenReturn(false);

        User loggedInUser = userService.login("test@example.com", "wrongPassword");

        assertNull(loggedInUser);
    }

    @Test
    void testLogin_failUserNotFound() {
        when(userRepository.findByEmail("unknown@example.com")).thenReturn(Optional.empty());

        User loggedInUser = userService.login("unknown@example.com", "anyPassword");

        assertNull(loggedInUser);
    }

    @Test
    void testGetUserById_found() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> found = userService.getUserById(1L);

        assertTrue(found.isPresent());
        assertEquals("test@example.com", found.get().getEmail());
    }

    @Test
    void testGetUserById_notFound() {
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<User> found = userService.getUserById(2L);

        assertFalse(found.isPresent());
    }

    @Test
    void testUpdateUser_foundAndUpdated() {
        User updatedUser = new User();
        updatedUser.setName("Jane");
        updatedUser.setPrenom("Smith");
        updatedUser.setEmail("jane@example.com");
        updatedUser.setAeroport("LHR");
        updatedUser.setRole("ADMIN");
        updatedUser.setPassword("newPassword");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User result = userService.updateUser(1L, updatedUser);

        assertNotNull(result);
        assertEquals("Jane", result.getName());
        assertEquals("Smith", result.getPrenom());
        assertEquals("jane@example.com", result.getEmail());
        assertEquals("LHR", result.getAeroport());
        assertEquals("ADMIN", result.getRole());
        assertEquals("newPassword", result.getPassword());
    }

    @Test
    void testUpdateUser_notFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        User result = userService.updateUser(99L, user);

        assertNull(result);
    }

    @Test
    void testFindUserByEmail_found() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Optional<User> result = userService.findUserByEmail("test@example.com");

        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());
    }

    @Test
    void testFindUserByEmail_notFound() {
        when(userRepository.findByEmail("unknown@example.com")).thenReturn(Optional.empty());

        Optional<User> result = userService.findUserByEmail("unknown@example.com");

        assertFalse(result.isPresent());
    }

    @Test
    void testFindByPhoneNumber_found() {
        when(userRepository.findByPhoneNumber("1234567890")).thenReturn(Optional.of(user));

        Optional<User> result = userService.findByPhoneNumber("1234567890");

        assertTrue(result.isPresent());
        assertEquals("1234567890", result.get().getPhoneNumber());
    }

    @Test
    void testFindByPhoneNumber_notFound() {
        when(userRepository.findByPhoneNumber("0000000000")).thenReturn(Optional.empty());

        Optional<User> result = userService.findByPhoneNumber("0000000000");

        assertFalse(result.isPresent());
    }

    @Test
    void testResetPassword_success() {
        when(userRepository.findByPhoneNumber("1234567890")).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User result = userService.resetPassword("1234567890", "newPass", "reset123");

        assertNotNull(result);
        assertNull(result.getResetCode());
        assertEquals("newPass", result.getPassword());
    }

    @Test
    void testResetPassword_wrongCode() {
        when(userRepository.findByPhoneNumber("1234567890")).thenReturn(Optional.of(user));

        User result = userService.resetPassword("1234567890", "newPass", "wrongCode");

        assertNull(result);
    }

    @Test
    void testResetPassword_userNotFound() {
        when(userRepository.findByPhoneNumber("0000000000")).thenReturn(Optional.empty());

        User result = userService.resetPassword("0000000000", "newPass", "anyCode");

        assertNull(result);
    }

    @Test
    void testSaveUser() {
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.save(user);

        assertNotNull(result);
        verify(userRepository, times(1)).save(user);
    }
}


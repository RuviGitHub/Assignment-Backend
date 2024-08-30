package lk.icbt.demo.service;

import lk.icbt.demo.dto.LoginDTO;
import lk.icbt.demo.dto.ResponseDTO;
import lk.icbt.demo.dto.UserDTO;
import lk.icbt.demo.entity.User;
import lk.icbt.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_UserAlreadyExists() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@example.com");
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(new User()));

        // Act
        ResponseDTO response = userService.registerUser(userDTO);

        // Assert
        assertEquals(409, response.getStatusCode());
        assertEquals("Error: User already exists with this email.", response.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testRegisterUser_NewUser() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("password123");
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(new User());

        // Act
        ResponseDTO response = userService.registerUser(userDTO);

        // Assert
        assertEquals(201, response.getStatusCode());
        assertEquals("Message: User registered successfully.", response.getMessage());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testLoginUser_UserNotFound() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("test@example.com");
        loginDTO.setPassword("password123");
        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.empty());

        // Act
        ResponseDTO response = userService.loginUser(loginDTO);

        // Assert
        assertEquals(401, response.getStatusCode());
        assertEquals("Error: User not found or account is deleted.", response.getMessage());
    }

    @Test
    void testLoginUser_InvalidCredentials() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("test@example.com");
        loginDTO.setPassword("password123");
        User user = new User();
        user.setPassword("wrongpassword");
        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(user));

        // Act
        ResponseDTO response = userService.loginUser(loginDTO);

        // Assert
        assertEquals(401, response.getStatusCode());
        assertEquals("Error: Invalid credentials.", response.getMessage());
    }

    @Test
    void testLoginUser_Success() {
        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("test@example.com");
        loginDTO.setPassword("password123");
        User user = new User();
        user.setPassword("password123");
        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(user));

        // Act
        ResponseDTO response = userService.loginUser(loginDTO);

        // Assert
        assertEquals(200, response.getStatusCode());
        assertEquals("Message: Login successful.", response.getMessage());
    }
}

package com.skillforge.services.auth;

import com.skillforge.dto.request.RegisterRequest;
import com.skillforge.entities.UserEntity;
import com.skillforge.enums.UserRole;
import com.skillforge.exceptions.InvalidCredentialsException;
import com.skillforge.repositories.UserRepository;
import com.skillforge.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;

    private JwtTokenProvider tokenProvider;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        passwordEncoder = new BCryptPasswordEncoder();
        tokenProvider = new JwtTokenProvider("replace-with-a-secure-secret-key-replace-me-1234567890123456", 900000, 2592000000L);
        authService = new AuthServiceImpl(userRepository, passwordEncoder, tokenProvider, authenticationManager);
    }

    @Test
    void registerCreatesNewUser() {
        RegisterRequest request = new RegisterRequest();
        request.setFullName("Test User");
        request.setEmail("test@example.com");
        request.setPassword("SecurePassword123");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(userRepository.save(any(UserEntity.class))).thenAnswer(invocation -> {
            UserEntity user = invocation.getArgument(0);
            user.setId(1L);
            return user;
        });

        var response = authService.register(request);

        assertNotNull(response.getAccessToken());
        assertNotNull(response.getRefreshToken());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void registerThrowsWhenEmailAlreadyExists() {
        RegisterRequest request = new RegisterRequest();
        request.setFullName("Test User");
        request.setEmail("test@example.com");
        request.setPassword("SecurePassword123");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        assertThrows(InvalidCredentialsException.class, () -> authService.register(request));
        verify(userRepository, never()).save(any());
    }
}

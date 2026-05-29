package com.skillforge.services.auth;

import com.skillforge.dto.request.LoginRequest;
import com.skillforge.dto.request.RefreshTokenRequest;
import com.skillforge.dto.request.RegisterRequest;
import com.skillforge.dto.response.AuthResponse;
import com.skillforge.dto.response.UserProfileResponse;
import com.skillforge.entities.UserEntity;
import com.skillforge.enums.UserRole;
import com.skillforge.exceptions.InvalidCredentialsException;
import com.skillforge.exceptions.ResourceNotFoundException;
import com.skillforge.repositories.UserRepository;
import com.skillforge.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider tokenProvider,
                           AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new InvalidCredentialsException("Email already in use");
        }

        UserEntity user = new UserEntity();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.USER);
        userRepository.save(user);

        return new AuthResponse(tokenProvider.createAccessToken(user.getId(), user.getEmail(), user.getRole().name()),
                tokenProvider.createRefreshToken(user.getId(), user.getEmail()));
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        return new AuthResponse(tokenProvider.createAccessToken(user.getId(), user.getEmail(), user.getRole().name()),
                tokenProvider.createRefreshToken(user.getId(), user.getEmail()));
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        if (!tokenProvider.validateToken(request.getRefreshToken())) {
            throw new InvalidCredentialsException("Invalid refresh token");
        }

        Long userId = Long.parseLong(tokenProvider.parseClaims(request.getRefreshToken()).getSubject());
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return new AuthResponse(tokenProvider.createAccessToken(user.getId(), user.getEmail(), user.getRole().name()),
                tokenProvider.createRefreshToken(user.getId(), user.getEmail()));
    }

    @Override
    public UserProfileResponse currentUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return new UserProfileResponse(user.getId(), user.getFullName(), user.getEmail(), user.getRole(), user.getAvatarUrl(), user.getBio(), user.isActive());
    }
}

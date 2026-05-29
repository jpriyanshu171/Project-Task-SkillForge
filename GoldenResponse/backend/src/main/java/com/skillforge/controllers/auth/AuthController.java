package com.skillforge.controllers.auth;

import com.skillforge.dto.request.LoginRequest;
import com.skillforge.dto.request.RefreshTokenRequest;
import com.skillforge.dto.request.RegisterRequest;
import com.skillforge.dto.response.ApiResponse;
import com.skillforge.dto.response.AuthResponse;
import com.skillforge.dto.response.UserProfileResponse;
import com.skillforge.services.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(true, "User registered successfully", authService.register(request)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Login successful", authService.login(request)));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<AuthResponse>> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Token refreshed", authService.refreshToken(request)));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserProfileResponse>> me(@AuthenticationPrincipal UserDetails userDetails) {
        UserProfileResponse profile = authService.currentUser(Long.parseLong(userDetails.getUsername()));
        return ResponseEntity.ok(new ApiResponse<>(true, "Profile retrieved", profile));
    }
}

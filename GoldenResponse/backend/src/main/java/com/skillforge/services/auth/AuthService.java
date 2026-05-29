package com.skillforge.services.auth;

import com.skillforge.dto.request.LoginRequest;
import com.skillforge.dto.request.RefreshTokenRequest;
import com.skillforge.dto.request.RegisterRequest;
import com.skillforge.dto.response.AuthResponse;
import com.skillforge.dto.response.UserProfileResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    AuthResponse refreshToken(RefreshTokenRequest request);
    UserProfileResponse currentUser(Long userId);
}

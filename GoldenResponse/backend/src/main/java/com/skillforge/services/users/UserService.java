package com.skillforge.services.users;

import com.skillforge.dto.request.RegisterRequest;
import com.skillforge.dto.response.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
    UserProfileResponse updateProfile(Long userId, RegisterRequest request);
}

package com.skillforge.dto.response;

import com.skillforge.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileResponse {
    private final Long id;
    private final String fullName;
    private final String email;
    private final UserRole role;
    private final String avatarUrl;
    private final String bio;
    private final boolean isActive;
}

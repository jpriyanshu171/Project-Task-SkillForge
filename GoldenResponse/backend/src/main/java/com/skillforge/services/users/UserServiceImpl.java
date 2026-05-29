package com.skillforge.services.users;

import com.skillforge.dto.request.RegisterRequest;
import com.skillforge.dto.response.UserProfileResponse;
import com.skillforge.entities.UserEntity;
import com.skillforge.exceptions.ResourceNotFoundException;
import com.skillforge.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserProfileResponse getProfile(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return map(user);
    }

    @Override
    public UserProfileResponse updateProfile(Long userId, RegisterRequest request) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        return map(user);
    }

    private UserProfileResponse map(UserEntity user) {
        return new UserProfileResponse(user.getId(), user.getFullName(), user.getEmail(), user.getRole(), user.getAvatarUrl(), user.getBio(), user.isActive());
    }
}

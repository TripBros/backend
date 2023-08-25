package com.example.tripbros.service;

import com.example.tripbros.domain.User;
import com.example.tripbros.dto.CreateOauthUserRequest;
import com.example.tripbros.dto.CreateUserRequest;
import com.example.tripbros.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Long save(User user) {
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }
}

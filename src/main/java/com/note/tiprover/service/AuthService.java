package com.note.tiprover.service;

import com.note.tiprover.dto.request.UserLoginRequest;
import com.note.tiprover.dto.request.UserRegisterRequest;
import com.note.tiprover.dto.response.UserResponse;
import com.note.tiprover.entity.User;
import com.note.tiprover.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse register (UserRegisterRequest dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new RuntimeException(dto.email() + " already exists");
        }
        User user = new User();
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        User saved = userRepository.save(user);

        return new UserResponse(saved.getUid(), saved.getEmail());
    }

    public UserResponse login (UserLoginRequest dto) {
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException(dto.email() + " not exists"));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException(dto.password() + " invalid password");
        }

        return new UserResponse(user.getUid(), user.getEmail());
    }
}

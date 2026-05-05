package com.note.tiprover.controllers;

import com.note.tiprover.dto.request.UserLoginRequest;
import com.note.tiprover.dto.request.UserRegisterRequest;
import com.note.tiprover.dto.response.UserResponse;
import com.note.tiprover.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRegisterRequest dto) {
        UserResponse response = authService.register(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserLoginRequest dto) {
        UserResponse response = authService.login(dto);
        return ResponseEntity.ok(response);
    }

}

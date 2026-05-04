package com.note.tiprover.dto.request;

public record UserRegisterRequest(
        String usr,
        String email,
        String password
){}

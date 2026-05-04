package com.note.tiprover.dto.request;

public record UserLoginRequest (
        String email,
        String password
){}
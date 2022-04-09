package com.santhumpol.backend_spring_boot.model;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;

    private String password;
}


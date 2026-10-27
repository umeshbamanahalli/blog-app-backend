package com.example.blog_app.security;

import com.example.blog_app.payloads.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthResponse {
    private String token;
    private UserDto user;
}

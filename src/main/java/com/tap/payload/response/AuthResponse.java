package com.tap.payload.response;

import com.tap.payload.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private  String message;
    private UserDto user;
}

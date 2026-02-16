package com.tap.service;

import com.tap.exceptions.UserException;
import com.tap.payload.dto.UserDto;
import com.tap.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse signup(UserDto userDto) throws UserException;
    AuthResponse login(UserDto userDto) throws UserException;
}

package com.tap.controller;

import com.tap.exceptions.UserException;
import com.tap.payload.dto.UserDto;
import com.tap.payload.response.AuthResponse;
import com.tap.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private  final AuthService authService;

    //  http://localhost:500//auth/signup
//
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(
            @RequestBody UserDto userDto
            ) throws UserException {
        return  ResponseEntity.ok(
                authService.signup(userDto)
        );

    }
//@PostMapping("/signup")
//public ResponseEntity<AuthResponse> signupHandler(@RequestBody UserDto userDto) throws UserException {
//
//    System.out.println("SIGNUP CONTROLLER CALLED");
//
//    return ResponseEntity.ok(authService.signup(userDto));
//}


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody UserDto userDto
    ) throws UserException {
        return  ResponseEntity.ok(
                authService.login(userDto)
        );

    }

}

package com.tap.controller;


import com.tap.configuration.AccessRules;
import com.tap.exceptions.UserException;
import com.tap.mapper.UserMapper;
import com.tap.modal.User;
import com.tap.payload.dto.UserDto;
import com.tap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    @PreAuthorize(AccessRules.ANY_USER)
    public ResponseEntity<UserDto> getUserProfile(
            @RequestHeader("Authorization") String jwt
    ) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @GetMapping("/{id}")
    @PreAuthorize(AccessRules.BRANCH_LEVEL)
    public ResponseEntity<UserDto> getUserById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws UserException, Exception {
        User user = userService.getUserById(id);

        return ResponseEntity.ok(UserMapper.toDto(user));
    }




}

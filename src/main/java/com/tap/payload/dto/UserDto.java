package com.tap.payload.dto;

import com.tap.domain.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private Long id;

    private String fullName;

    private String email;

    private  String phone;

    private UserRole role;

    private String password;
    private BranchDTO branch;
    private String branchName;
    private Long branchId;
    private Long storeId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;

    public UserDto(
            Long id,
            String email,
            String fullName,
            UserRole role,
            String branchName,
            LocalDateTime lastLogin
    ) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.branchName = branchName;
        this.lastLogin = lastLogin;
    }

}

package com.tap.payload.dto;

import com.tap.domain.StoreStatus;
import com.tap.modal.StoreContact;
import com.tap.modal.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreDto {

    private Long id;
    private String brand;
    private UserDto storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private StoreStatus status;

    private StoreContact contact;

}

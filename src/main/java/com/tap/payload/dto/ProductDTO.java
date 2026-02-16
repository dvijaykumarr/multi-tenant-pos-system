package com.tap.payload.dto;

import com.tap.modal.Store;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductDTO {

    private Long id;


    private String name;


    private String sku;

    private String description;

    private Double mrp;

    private Double sellingPrice;

    private String brand;

    private String image;

    // private Category category;
   private Long categoryId;
    private Long storeId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;



}

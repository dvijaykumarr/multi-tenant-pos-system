package com.tap.payload.dto;

import com.tap.modal.Store;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;


    private String name;


    private String sku;

    private String description;

    private Double mrp;

    private Double sellingPrice;

    private String brand;

    private String image;

     private CategoryDTO category;

   private Long categoryId;
    private Long storeId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ProductDTO(
            Long id,
            String name,
            String sku,
            String description,
            Double mrp,
            Double sellingPrice,
            String brand,
            Long categoryId,
            Long storeId,
            String image,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.description = description;
        this.mrp = mrp;
        this.sellingPrice = sellingPrice;
        this.brand = brand;
        this.categoryId = categoryId;
        this.storeId = storeId;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }



}

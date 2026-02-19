package com.tap.mapper;

import com.tap.modal.Category;
import com.tap.modal.Product;
import com.tap.modal.Store;
import com.tap.payload.dto.ProductDTO;

public class ProductMapper {


    public static ProductDTO toDTO(Product product){

        return  ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .brand(product.getBrand())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null) // correct - using category id
                .category(CategoryMapper.toDto(product.getCategory()))
                .storeId(product.getStore() != null ?product.getStore().getId():null)
                .image(product.getImage())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();

    }

    public static Product toEntity(ProductDTO productDTO,
                                   Store store,
                                   Category category){

        return Product.builder()
                .name(productDTO.getName())
                .store(store)
                .category(category)
                .sku(productDTO.getSku())
                .description(productDTO.getDescription())
                .mrp(productDTO.getMrp())
                .sellingPrice(productDTO.getSellingPrice())
                .image(productDTO.getImage()) // <-- added this
                .brand(productDTO.getBrand())
                .build();
    }
}

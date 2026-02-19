package com.tap.mapper;

import com.tap.modal.Category;
import com.tap.payload.dto.CategoryDTO;

public class CategoryMapper {

    public static CategoryDTO toDto(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .storeId(category.getStore() != null?category.getStore().getId():null)
                .build();
    }
}

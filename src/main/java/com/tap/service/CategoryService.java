package com.tap.service;

import com.tap.exceptions.UserException;
import com.tap.payload.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO dto) throws Exception;
    List<CategoryDTO> getCategoriesByStore(Long storeId);
    CategoryDTO updateCategory(Long storeId, CategoryDTO dto) throws Exception;
    void deleteCategory(Long id) throws Exception;

}

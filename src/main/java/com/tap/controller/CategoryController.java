package com.tap.controller;


import com.tap.configuration.AccessRules;
import com.tap.payload.dto.CategoryDTO;
import com.tap.payload.response.ApiResponse;
import com.tap.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping
    @PreAuthorize(AccessRules.STORE_LEVEL)  // createCategory
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) throws Exception {
        return ResponseEntity.ok(
                categoryService.createCategory(categoryDTO)
        );

    }


    @GetMapping("/store/{storeId}")
    @PreAuthorize(AccessRules.ALL_STAFF)    // getCategoriesByStoreId
    public ResponseEntity<List<CategoryDTO>> getCategoriesByStoreId(
            @PathVariable Long storeId) throws Exception {
        return ResponseEntity.ok(
                categoryService.getCategoriesByStore(storeId)
        );

    }

    @PutMapping("/{id}")
    @PreAuthorize(AccessRules.STORE_LEVEL)  // updateCategory
    public ResponseEntity<CategoryDTO> updateCategory(
            @RequestBody CategoryDTO categoryDTO,
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(
                categoryService.updateCategory(id, categoryDTO)
        );

    }


    @DeleteMapping("/{id}")
    @PreAuthorize(AccessRules.STORE_LEVEL)  // deleteCategory
    public ResponseEntity<ApiResponse> deleteCategory(
            @PathVariable Long id) throws Exception {

        categoryService.deleteCategory(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Category deleted successfully");

        return ResponseEntity.ok(apiResponse);

    }
















}

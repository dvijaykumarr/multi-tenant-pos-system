package com.tap.controller;


import com.tap.configuration.AccessRules;
import com.tap.modal.Product;
import com.tap.modal.User;
import com.tap.payload.dto.ProductDTO;
import com.tap.payload.response.ApiResponse;
import com.tap.service.ProductService;
import com.tap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;



    @PostMapping ()
    @PreAuthorize(AccessRules.STORE_LEVEL)  // create
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO,
                                             @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(
                productService.createProduct(
                        productDTO, user

                )
        );

    }


    @GetMapping("/store/{storeId}")
    @PreAuthorize(AccessRules.ALL_STAFF)    // getStoreById
    public ResponseEntity<List<ProductDTO>> getStoreById(@PathVariable Long storeId,
                                                         @RequestHeader("Authorization") String jwt) throws Exception {

        return ResponseEntity.ok(
                productService.getProductsByStoreId(
                        storeId

                )
        );

    }


    @PatchMapping("/{id}")
    @PreAuthorize(AccessRules.STORE_LEVEL)  // update
    public ResponseEntity<ProductDTO> update(@PathVariable Long id,
                                             @RequestBody ProductDTO productDTO,
                                             @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(
                productService.updateProduct(
                        id,
                        productDTO, user

                )
        );

    }

    @GetMapping("/store/{storeId}/search")
    @PreAuthorize(AccessRules.ALL_STAFF)    // searchByKeyword
    public ResponseEntity<List<ProductDTO>> searchByKeyword(@PathVariable Long storeId,
                                                         @RequestParam String keyword,
                                                         @RequestHeader("Authorization") String jwt) throws Exception {

        return ResponseEntity.ok(
                productService.searchByKeyword(
                        storeId,
                        keyword

                )
        );

    }


    @DeleteMapping("/{id}")
    @PreAuthorize(AccessRules.STORE_LEVEL)  // delete
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id,
                                              @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.getUserFromJwtToken(jwt);
        productService.deleteProduct(
                id,
                user

        );

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Product deleted Successfully");

        return ResponseEntity.ok(
                apiResponse
        );



    }
























}

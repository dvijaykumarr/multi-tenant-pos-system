package com.tap.service;

import com.tap.modal.User;
import com.tap.payload.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO, User user) throws Exception;
    ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) throws Exception;
    void deleteProduct(Long id, User user) throws Exception;
    List<ProductDTO> getProductsByStoreId(Long storeId);
    List<ProductDTO> searchByKeyword(Long storeId, String keyword);


}

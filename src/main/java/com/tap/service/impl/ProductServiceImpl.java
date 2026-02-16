package com.tap.service.impl;

import com.tap.mapper.ProductMapper;
import com.tap.modal.Product;
import com.tap.modal.Store;
import com.tap.modal.User;
import com.tap.payload.dto.ProductDTO;
import com.tap.repository.ProductRepository;
import com.tap.repository.StoreRepository;
import com.tap.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private  final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) throws Exception {

        Store store = storeRepository.findById(
                productDTO.getStoreId()
        ).orElseThrow(
                ()-> new Exception("Store not found")
        );

        Product product = ProductMapper.toEntity(productDTO, store);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) throws Exception {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new Exception("product not found")
        );

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSku(productDTO.getSku());
        product.setImage(product.getImage());
        product.setMrp(product.getMrp());
        product.setSellingPrice(product.getSellingPrice());
        product.setBrand(product.getBrand());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new Exception("Product not found")
        );

        productRepository.delete(product);

    }

    @Override
    public List<ProductDTO> getProductsByStoreId(Long storeId) {

         List<Product> products = productRepository.findByStoreId(storeId);
         return products.stream()
                 .map(ProductMapper::toDTO)
                 .collect(Collectors.toList());

    }

    @Override
    public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {

        List<Product> products = productRepository.searchByKeyword(storeId,keyword);
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());




    }
}

package com.tap.repository;


import com.tap.modal.Product;

import com.tap.payload.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.tap.payload.dto.CategorySalesDTO;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findByStoreId(Long StoreId);

    @Query(
            "SELECT p FROM Product p "+
                    "WHERE p.store.id = :storeId AND ("+
                    "LOWER(p.name) LIKE LOWER (CONCAT('%', :query, '%'))" +
                    "Or LOWER(p.brand) LIKE LOWER (CONCAT('%', :query, '%'))" +
                    "Or LOWER(p.sku) LIKE LOWER (CONCAT('%', :query, '%'))" +
                    ")"

    )
    List<Product> searchByKeyword(@Param("storeId") Long storeId, @Param("query") String keyword);


    // store analysis

    @Query("SELECT COUNT(p) FROM Product p WHERE p.store.storeAdmin.id = :storeAdminId")
    int countByStoreAdminId(@Param("storeAdminId") Long storeAdminId);

    @Query("""
        SELECT new com.tap.payload.dto.CategorySalesDTO(
            p.category.name,
            SUM(oi.quantity * p.sellingPrice)
        )
        FROM OrderItem oi
        JOIN oi.product p
        WHERE p.store.storeAdmin.id = :storeAdminId
        GROUP BY p.category.name
    """)
    List<CategorySalesDTO> getSalesGroupedByCategory(@Param("storeAdminId") Long storeAdminId);

    @Query("""
        SELECT new com.tap.payload.dto.ProductDTO(
                p.id,
                p.name,
                p.sku,
                p.description,
                p.mrp,
                p.sellingPrice,
                p.brand,
                p.category.id,
                p.store.id,
                p.image,
                p.createdAt,
                p.updatedAt
            )
        FROM Product p
        WHERE p.store.storeAdmin.id = :storeAdminId 
        AND p.id NOT IN (
            SELECT i.product.id 
            FROM Inventory i 
            WHERE i.quantity > 0
        )
    """)
    List<ProductDTO> findLowStockProducts(@Param("storeAdminId") Long storeAdminId);


}

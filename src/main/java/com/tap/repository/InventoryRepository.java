package com.tap.repository;

import com.tap.modal.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findByProductIdAndBranchId(Long productId, Long branchId);
    List<Inventory> findByBranchId(Long branchId);

    // for analytics
    @Query("""
        SELECT COUNT(i)
        FROM Inventory i
        JOIN i.product p
        WHERE i.branch.id = :branchId
        AND i.quantity <= 5
    """)
    int countLowStockItems(@Param("branchId") Long branchId);


}

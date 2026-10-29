package com.tap.repository;

import com.tap.modal.Order;
import com.tap.modal.User;
import com.tap.payload.storeAnalytics.BranchSalesDTO;
import com.tap.payload.storeAnalytics.PaymentInsightDTO;
import com.tap.payload.storeAnalytics.TimeSeriesPointDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Always use User (entity) in the repository layer.
    //The repository layer should only deal with entities — that's its job.
    // The conversion to UserDto should happen in the service layer or controller layer.


    List<Order> findByCustomer_Id(Long customerId);
    List<Order> findByBranch_Id(Long branchId);
    List<Order> findByCashier_Id(Long cashierId);
    List<Order> findByBranch_IdAndCreatedAtBetween(Long branchId, LocalDateTime from, LocalDateTime to);

    List<Order> findByCashierAndCreatedAtBetween(
            User cashier, LocalDateTime from, LocalDateTime to
    );

    List<Order> findTop5ByBranch_IdOrderByCreatedAtDesc(Long branchId);

    //these repository methods are for analytics
    @Query("""

        select sum(o.totalAmount)
        from Order o 
        where o.branch.id = :branchId
        AND o.createdAt BETWEEN :start AND :end
    """)
    Optional<BigDecimal> getTotalSalesBetween(@Param("branchId")Long branchId,
                                              @Param("start") LocalDateTime start,
                                              @Param("end") LocalDateTime end);


    @Query("""

        SELECT u.id, u.fullName, SUM(o.totalAmount) as totalRevenue
        FROM Order o 
        JOIN o.cashier u 
        WHERE o.branch.id = :branchId
        GROUP BY u.id, u.fullName
        order by totalRevenue DESC
    """)
    List<Object[]> getTopCashierByRevenue(@Param("branchId") Long branchId);

    @Query("""
        SELECT COUNT(o)
        FROM Order o
        WHERE o.branch.id = :branchId
        AND DATE(o.createdAt) = :date
    """)
    int countOrdersByBranchAndDate(@Param("branchId") Long branchId,
                                   @Param("date") LocalDate date);

    @Query("""
        SELECT COUNT(DISTINCT o.cashier.id)
        FROM Order o
        WHERE o.branch.id = :branchId
        AND DATE(o.createdAt) = :date
    """)
    int countDistinctCashiersByBranchAndDate(@Param("branchId") Long branchId,
                                             @Param("date") LocalDate date);

    @Query("""
    SELECT o.paymentType, SUM(o.totalAmount), COUNT(o)
    FROM Order o
    WHERE o.branch.id = :branchId
    AND DATE(o.createdAt) = :date
    GROUP BY o.paymentType
""")
    List<Object[]> getPaymentBreakdownByMethod(
            @Param("branchId") Long branchId,
            @Param("date") LocalDate date
    );


    @Query("SELECT SUM(o.totalAmount) FROM Order o " +
            "WHERE o.branch.store.storeAdmin.id = :storeAdminId")
    Optional<Double> sumTotalSalesByStoreAdmin(@Param("storeAdminId") Long storeAdminId);


    @Query("SELECT COUNT(o) FROM Order o WHERE o.branch.store.storeAdmin.id = :storeAdminId")
    int countByStoreAdminId(@Param("storeAdminId") Long storeAdminId);

    @Query("""
    SELECT o FROM Order o 
    WHERE o.branch.store.storeAdmin.id = :storeAdminId 
    AND o.createdAt BETWEEN :start AND :end
""")
    List<Order> findAllByStoreAdminAndCreatedAtBetween(@Param("storeAdminId") Long storeAdminId,
                                                       @Param("start") LocalDateTime start,
                                                       @Param("end") LocalDateTime end);

    @Query("""
    SELECT new com.tap.payload.storeAnalytics.TimeSeriesPointDTO(
        o.createdAt,
        SUM(o.totalAmount)
    )
    FROM Order o
    WHERE o.branch.store.storeAdmin.id = :storeAdminId
     AND o.createdAt BETWEEN :start AND :end
    GROUP BY o.createdAt
    ORDER BY o.createdAt
""")
    List<TimeSeriesPointDTO> getDailySales(@Param("storeAdminId") Long storeAdminId,
                                           @Param("start") LocalDateTime start,
                                           @Param("end") LocalDateTime end);



    @Query("""
        SELECT new com.tap.payload.storeAnalytics.PaymentInsightDTO(
            o.paymentType,
            SUM(o.totalAmount)
        )
        FROM Order o
        WHERE o.branch.store.storeAdmin.id = :storeAdminId
        GROUP BY o.paymentType
    """)
    List<PaymentInsightDTO> getSalesByPaymentMethod(@Param("storeAdminId") Long storeAdminId);



    @Query("""
        SELECT new com.tap.payload.storeAnalytics.BranchSalesDTO(
            o.branch.name,
            SUM(o.totalAmount)
        )
        FROM Order o
        WHERE o.branch.store.storeAdmin.id = :storeAdminId
        GROUP BY o.branch.id
    """)
    List<BranchSalesDTO> getSalesByBranch(@Param("storeAdminId") Long storeAdminId);





}

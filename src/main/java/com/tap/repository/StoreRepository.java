package com.tap.repository;

import com.tap.domain.StoreStatus;
import com.tap.modal.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByStoreAdminId(Long adminId);

    Long countByStatus(StoreStatus status);

    List<Store> findByStatus(StoreStatus status);

    @Query("""
        SELECT count(s) 
        from Store s 
        where DATE(s.createdAt) = :date
    """)
    Long countByDate(LocalDate date);

    @Query(value = """
        SELECT DATE(created_at), COUNT(*)
        FROM store
        WHERE created_at >= :startDate
        GROUP BY DATE(created_at)
        ORDER BY DATE(created_at)
        """, nativeQuery = true)
    List<Object[]> getStoreRegistrationStats(@Param("startDate")LocalDateTime startDate);



}

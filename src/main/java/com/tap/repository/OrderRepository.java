package com.tap.repository;

import com.tap.modal.Order;
import com.tap.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Always use User (entity) in the repository layer.
    //The repository layer should only deal with entities — that's its job.
    // The conversion to UserDto should happen in the service layer or controller layer.


    List<Order> findByCustomerId(Long customerId);
    List<Order> findByBranchId(Long branchId);
    List<Order> findByCashierId(Long cashierId);
    List<Order> findByBranchIdAndCreatedAtBetween(Long branchId, LocalDateTime from, LocalDateTime to);

    List<Order> findByCashierAndCreatedAtBetween(
            User cashier, LocalDateTime from, LocalDateTime to
    );

    List<Order> findTop5ByBranchIdOrderByCreatedAtDesc(Long branchId);

}

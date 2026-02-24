package com.tap.payload.dto;



import com.tap.domain.PaymentType;
import com.tap.modal.Customer;

import com.tap.modal.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {


    private Long id;

    private Double totalAmount;

    private LocalDateTime createdAt;


    private Long branchId;
    private Long customerId;

    private BranchDTO branch;


    private UserDto cashier;


    private Customer customer;

    private PaymentType paymentType;

    private List<OrderItemDTO> items;

}

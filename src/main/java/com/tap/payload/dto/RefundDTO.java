package com.tap.payload.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tap.domain.PaymentType;
import com.tap.modal.Branch;
import com.tap.modal.Order;
import com.tap.modal.ShiftReport;
import com.tap.modal.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundDTO {

    private Long id;


    private OrderDto order;
    private Long orderId;

    private String reason;

    private  Double amount;

//    private ShiftReport shiftReport;
    private Long shiftReportId;

    private UserDto cashier;
    private String cashierName;

    private BranchDTO branch;
    private Long branchId;


    private LocalDateTime createdAt;
    private PaymentType paymentType;
}

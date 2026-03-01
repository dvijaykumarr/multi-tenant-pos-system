package com.tap.payload.dto;

import com.tap.modal.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShiftReportDTO {

    private Long id;

    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;

    private Double totalSales;
    private Double totalRefunds;
    private Double netSale;
    private int totalOrders;


    private UserDto cashier;
    private Long cashierId;
    private Long branchId;


    private BranchDTO branch;


    private List<PaymentSummary> paymentSummaries;


    private List<ProductDTO> topSellingProducts;


    private List<OrderDto> recentOrders;


    private List<RefundDTO> refunds;

}

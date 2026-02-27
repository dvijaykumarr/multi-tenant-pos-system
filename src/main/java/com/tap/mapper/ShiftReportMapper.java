package com.tap.mapper;

import com.tap.modal.Order;
import com.tap.modal.Product;
import com.tap.modal.Refund;
import com.tap.modal.ShiftReport;
import com.tap.payload.dto.OrderDto;
import com.tap.payload.dto.ProductDTO;
import com.tap.payload.dto.RefundDTO;
import com.tap.payload.dto.ShiftReportDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ShiftReportMapper {
    public static ShiftReportDTO toDTO(ShiftReport entity){
        return ShiftReportDTO.builder()
                .id(entity.getId())
                .shiftStart(entity.getShiftStart())
                .shiftEnd(entity.getShiftEnd())
                .totalSales(entity.getTotalSales())
                .totalOrders(entity.getTotalOrders())
                .netSale(entity.getNetSale())
                .totalOrders(entity.getTotalOrders())
                .cashier(UserMapper.toDto(entity.getCashier()))
                .cashierId(entity.getCashier().getId())
                .branchId(entity.getBranch().getId())
                .recentOrders(mapOrders(entity.getRecentOrders()))
                .topSellingProducts(mapProducts(entity.getTopSellingProducts()))
                .refunds(mapRefunds(entity.getRefunds()))
                .paymentSummaries(entity.getPaymentSummaries())
                .build();

    }

    private static List<RefundDTO> mapRefunds(List<Refund> refunds) {
        if(refunds == null || refunds.isEmpty()){return null;}

        return refunds.stream().map(RefundMapper::toDTO).collect(Collectors.toList());
    }

    private static List<ProductDTO> mapProducts(List<Product> topSellingProducts) {
        if(topSellingProducts == null || topSellingProducts.isEmpty()){return null;}

        return  topSellingProducts.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    private static List<OrderDto> mapOrders(List<Order> recentOrders) {

        if(recentOrders == null || recentOrders.isEmpty()){return  null;}

        return recentOrders.stream().map(OrderMapper::toDTO).collect(Collectors.toList());

    }

}

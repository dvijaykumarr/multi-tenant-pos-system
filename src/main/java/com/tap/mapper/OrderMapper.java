package com.tap.mapper;

import com.tap.modal.Order;
import com.tap.payload.dto.OrderDto;
import com.tap.payload.dto.OrderItemMapper;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDto toDTO(Order order){

        return OrderDto.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .branchId(order.getBranch().getId())
                .cashier(UserMapper.toDto(order.getCashier()))
                .customer(order.getCustomer())
                .paymentType(order.getPaymentType())
                .createdAt(order.getCreatedAt())
                .items(order.getItems().stream().map(
                        OrderItemMapper::toDTO)
                        .collect(Collectors.toList())
                )
                .build();

    }
}

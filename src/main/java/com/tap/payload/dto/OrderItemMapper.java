package com.tap.payload.dto;

import com.tap.mapper.ProductMapper;
import com.tap.modal.OrderItem;

public class OrderItemMapper {

    public static OrderItemDTO toDTO(OrderItem item){

        if(item == null) return null;
        return OrderItemDTO.builder()
                .id(item.getId())
                .productId(item.getProduct().getId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .product(ProductMapper.toDTO(item.getProduct()))
                .build();

    }
}

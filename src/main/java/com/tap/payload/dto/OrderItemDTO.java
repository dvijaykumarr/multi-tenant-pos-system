package com.tap.payload.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDTO {

    private  Long id;

    private Integer quantity;

    private Double price;


    private ProductDTO product;

    private Long productId;

    private Long orderId;
}

package com.tap.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategorySalesDTO {

    private String categoryName;
    private Double totalSales;
    private Long quantitySold;

    public CategorySalesDTO(
            String categoryName,
            Double totalSales
    ) {
        this.categoryName = categoryName;
        this.totalSales = totalSales;
    }

    public CategorySalesDTO(String categoryName, Long totalSales) {
        this.categoryName = categoryName;
        this.totalSales = totalSales.doubleValue();
    }
}

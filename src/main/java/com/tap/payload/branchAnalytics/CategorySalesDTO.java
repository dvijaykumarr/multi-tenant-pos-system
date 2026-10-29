package com.tap.payload.branchAnalytics;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategorySalesDTO {

    private String categoryName;
    private Double totalSales;
    private Long quantitySold;

}

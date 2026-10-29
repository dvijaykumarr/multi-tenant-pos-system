package com.tap.payload.storeAnalytics;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BranchSalesDTO {
    private String branchName;
    private double totalSales;
}

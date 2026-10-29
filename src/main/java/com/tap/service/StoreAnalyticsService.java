package com.tap.service;

import com.tap.payload.dto.CategorySalesDTO;
import com.tap.payload.storeAnalytics.*;

import java.util.List;

public interface StoreAnalyticsService {

    StoreOverviewDTO getSToreOverview(Long storeAdminId);
    TimeSeriesDataDTO getSalesTrends(Long storeAdminId, String period);

    List<TimeSeriesPointDTO> getMonthlySalesGraph(Long storeAdminId);
    List<TimeSeriesPointDTO> getDailySalesGraph(Long storeAdminId);

    //List<CategorySalesDTO> getSalesByCategory(Long St)
    public List<CategorySalesDTO> getSalesByCategory(Long storeAdminId);

    public List<PaymentInsightDTO> getPaymentBreakdown(Long storeAdminId);

    List<PaymentInsightDTO> getSalesByPaymentMethod(Long storeAdminID);
    List<BranchSalesDTO> getSalesByBranch(Long storeAdminId);

    List<PaymentInsightDTO> getPaymentBreakDown(Long storeAdminId);

    BranchPerformanceDTO getBranchPerformance(Long storeAdminId);

    StoreAlertDTO getStoreAlert(Long storeAdminId);


}

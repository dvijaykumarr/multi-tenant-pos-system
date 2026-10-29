package com.tap.service;

import com.tap.modal.PaymentSummary;
import com.tap.payload.branchAnalytics.BranchDashboardOverviewDTO;
import com.tap.payload.branchAnalytics.CashierPerformanceDTO;
import com.tap.payload.branchAnalytics.CategorySalesDTO;
import com.tap.payload.branchAnalytics.ProductPerformanceDTO;
import com.tap.payload.branchAnalytics.DailySalesDTO;

import java.time.LocalDate;
import java.util.List;

public interface BranchAnalyticsService {

    List<DailySalesDTO> getDailySalesChart(Long branchId, int days);
    List<ProductPerformanceDTO> getTopProductsByQuantityWithPercentage(Long branchId);
    List<CashierPerformanceDTO> getTopCashierPerformanceByOrders(Long branchId);
    List<CategorySalesDTO> getCategoryWiseSalesBreakdown(Long branchId, LocalDate date);
    BranchDashboardOverviewDTO getBranchOverview(Long branchId);
    List<PaymentSummary> getPaymentMethodBreakdown(Long branchId, LocalDate date);
}

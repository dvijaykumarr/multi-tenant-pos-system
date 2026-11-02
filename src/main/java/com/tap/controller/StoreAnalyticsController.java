package com.tap.controller;


import com.tap.configuration.AccessRules;
import com.tap.payload.dto.CategorySalesDTO;
import com.tap.payload.storeAnalytics.*;
import com.tap.service.StoreAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store/analytics")
public class StoreAnalyticsController {

    private final StoreAnalyticsService storeAnalyticsService;


    @GetMapping("/{storeAdminId}/overview")
    @PreAuthorize(AccessRules.STORE_LEVEL)
    public StoreOverviewDTO getStoreOverview(@PathVariable Long storeAdminId) {
        return storeAnalyticsService.getSToreOverview(storeAdminId);
    }

    @PreAuthorize(AccessRules.STORE_LEVEL)
    @GetMapping("/{storeAdminId}/sales-trends")
    public TimeSeriesDataDTO getSalesTrends(@PathVariable Long storeAdminId,
                                            @RequestParam String period) {
        return storeAnalyticsService.getSalesTrends(storeAdminId, period); // implement logic if needed
    }

    // 📅 Monthly Sales Chart (line)
    @PreAuthorize(AccessRules.STORE_LEVEL)
    @GetMapping("/{storeAdminId}/sales/monthly")
    public List<TimeSeriesPointDTO> getMonthlySales(@PathVariable Long storeAdminId) {
        return storeAnalyticsService.getMonthlySalesGraph(storeAdminId);
    }

    // 🗓️ Daily Sales Chart (line)
    @PreAuthorize(AccessRules.STORE_LEVEL)
    @GetMapping("/{storeAdminId}/sales/daily")
    public List<TimeSeriesPointDTO> getDailySales(@PathVariable Long storeAdminId) {
        return storeAnalyticsService.getDailySalesGraph(storeAdminId);
    }

    // 📚 Sales by Product Category (pie/bar)
    @PreAuthorize(AccessRules.STORE_LEVEL)
    @GetMapping("/{storeAdminId}/sales/category")
    public List<CategorySalesDTO> getSalesByCategory(@PathVariable Long storeAdminId) {
        return storeAnalyticsService.getSalesByCategory(storeAdminId);
    }

    // 💳 Sales by Payment Method (pie)
    @PreAuthorize(AccessRules.STORE_LEVEL)
    @GetMapping("/{storeAdminId}/sales/payment-method")
    public List<PaymentInsightDTO> getSalesByPaymentMethod(@PathVariable Long storeAdminId) {
        return storeAnalyticsService.getSalesByPaymentMethod(storeAdminId);
    }

    // 📍 Sales by Branch (bar)
    @PreAuthorize(AccessRules.STORE_LEVEL)
    @GetMapping("/{storeAdminId}/sales/branch")
    public List<BranchSalesDTO> getSalesByBranch(@PathVariable Long storeAdminId) {
        return storeAnalyticsService.getSalesByBranch(storeAdminId);
    }

    // 💵 Payment Breakdown (Cash, UPI, Card)
    @PreAuthorize(AccessRules.STORE_LEVEL)
    @GetMapping("/{storeAdminId}/payments")
    public List<PaymentInsightDTO> getPaymentBreakdown(@PathVariable Long storeAdminId) {
        return storeAnalyticsService.getPaymentBreakdown(storeAdminId);
    }

    // 🏘️ Branch Performance
    @PreAuthorize(AccessRules.STORE_LEVEL)
    @GetMapping("/{storeAdminId}/branch-performance")
    public BranchPerformanceDTO getBranchPerformance(@PathVariable Long storeAdminId) {
        return storeAnalyticsService.getBranchPerformance(storeAdminId);
    }

    // ⚠️ Alerts and Health Monitoring
    @PreAuthorize(AccessRules.STORE_LEVEL)
    @GetMapping("/{storeAdminId}/alerts")
    public StoreAlertDTO getStoreAlerts(@PathVariable Long storeAdminId) {
        return storeAnalyticsService.getStoreAlert(storeAdminId);
    }
}

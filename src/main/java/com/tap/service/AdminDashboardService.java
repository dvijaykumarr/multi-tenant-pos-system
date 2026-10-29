package com.tap.service;

import com.tap.payload.adminAnalytics.DashBoardSummaryDTO;
import com.tap.payload.adminAnalytics.StoreRegistrationStateDTO;
import com.tap.payload.adminAnalytics.StoreStatusDistributionDTO;

import java.util.List;

public interface AdminDashboardService {

    DashBoardSummaryDTO getDashboardSummary();
    List<StoreRegistrationStateDTO> getLast7DaysRegistrationStats();
    StoreStatusDistributionDTO getStoreStatusDistribution();

}

package com.tap.controller;


import com.tap.payload.adminAnalytics.DashBoardSummaryDTO;
import com.tap.payload.adminAnalytics.StoreRegistrationStateDTO;
import com.tap.payload.adminAnalytics.StoreStatusDistributionDTO;
import com.tap.service.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/super-admin")
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    @GetMapping("/dashboard/summary")
    public ResponseEntity<DashBoardSummaryDTO> getDashboardSummary(){

        return ResponseEntity.ok(adminDashboardService.getDashboardSummary());
    }

    @GetMapping("/dashboard/store-registrations")
    public ResponseEntity<List<StoreRegistrationStateDTO>> getLast7DaysRegistrationStats(){

        return ResponseEntity.ok(adminDashboardService.getLast7DaysRegistrationStats());
    }

    @GetMapping("/dashboard/store-status-distribution")
    public ResponseEntity<StoreStatusDistributionDTO> getStoreStatusDistribution(){

        return ResponseEntity.ok(adminDashboardService.getStoreStatusDistribution());
    }
















}

package com.tap.controller;


import com.tap.configuration.AccessRules;
import com.tap.payload.dto.ShiftReportDTO;
import com.tap.service.ShiftReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shift-reports")
public class ShiftReportController {

    private final ShiftReportService shiftReportService;

    @PostMapping("/start")
    @PreAuthorize(AccessRules.CASHIER)       // startShift
    public ResponseEntity<ShiftReportDTO> startShift() throws Exception {
        return ResponseEntity.ok(
                shiftReportService.startShift()
        );
    }

    @PatchMapping("/end")
    @PreAuthorize(AccessRules.CASHIER)       // endShift
    public ResponseEntity<ShiftReportDTO> endShift() throws Exception {
        return ResponseEntity.ok(
                shiftReportService.endShift(null, null)
        );
    }

    @GetMapping("/current")
    @PreAuthorize(AccessRules.CASHIER)       // getCurrentShiftProgress
    public ResponseEntity<ShiftReportDTO> getCurrentShiftProgress() throws Exception {
        return ResponseEntity.ok(

                shiftReportService.getCurrentShiftProgress(null)
        );
    }


    @GetMapping("/cashier/{cashierId}/by-date")
    @PreAuthorize(AccessRules.ALL_STAFF) // getShiftReportByDate
    public ResponseEntity<ShiftReportDTO> getShiftReportByDate(
            @PathVariable Long cashierId,
            @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDateTime date
            ) throws Exception {
        return ResponseEntity.ok(

                shiftReportService.getShiftReportByCashierAndDate(cashierId, date)
        );
    }

    @GetMapping("/cashier/{cashierId}")
    @PreAuthorize(AccessRules.ALL_STAFF)     // getShiftReportByCashier
    public ResponseEntity<List<ShiftReportDTO>> getShiftReportByCashier(
            @PathVariable Long cashierId
    ) throws Exception {
        return ResponseEntity.ok(

                shiftReportService.getShiftReportByCashierId(cashierId)
        );
    }


    @GetMapping("/branch/{branchId}")
    @PreAuthorize(AccessRules.BRANCH_LEVEL)  // getShiftReportByBranch
    public ResponseEntity<List<ShiftReportDTO>> getShiftReportByBranch(
            @PathVariable Long branchId
    ) throws Exception {
        return ResponseEntity.ok(

                shiftReportService.getShiftReportByBranchId(branchId)
        );
    }



    @GetMapping("/{id}")
    @PreAuthorize(AccessRules.ALL_STAFF)     // getShiftReportById
    public ResponseEntity<ShiftReportDTO> getShiftReportById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(

                shiftReportService.getShiftReportById(id)
        );
    }






}

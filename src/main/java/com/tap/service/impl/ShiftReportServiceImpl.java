package com.tap.service.impl;

import com.tap.exceptions.UserException;
import com.tap.payload.dto.ShiftReportDTO;
import com.tap.service.ShiftReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ShiftReportServiceImpl implements ShiftReportService {
    @Override
    public ShiftReportDTO startShift(Long cashierId, Long branchId, LocalDateTime shiftStart) throws Exception {
        return null;
    }

    @Override
    public ShiftReportDTO endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception {
        return null;
    }

    @Override
    public ShiftReportDTO getShiftReportById(Long id) {
        return null;
    }

    @Override
    public List<ShiftReportDTO> getAllShiftReports() {
        return List.of();
    }

    @Override
    public List<ShiftReportDTO> getShiftReportByBranchId(Long branchId) {
        return List.of();
    }

    @Override
    public List<ShiftReportDTO> getShiftReportByCashierId(Long cashierId) {
        return List.of();
    }

    @Override
    public ShiftReportDTO getCurrentShiftProgress(Long cashierId) throws UserException {
        return null;
    }

    @Override
    public ShiftReportDTO getShiftReportByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception {
        return null;
    }
}

package com.tap.service;

import com.tap.exceptions.UserException;
import com.tap.modal.ShiftReport;
import com.tap.payload.dto.ShiftReportDTO;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.List;

public interface ShiftReportService {

    ShiftReportDTO startShift(Long cashierId, Long branchId, LocalDateTime shiftStart) throws Exception;
    ShiftReportDTO endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception;

    ShiftReportDTO getShiftReportById(Long id);

    List<ShiftReportDTO> getAllShiftReports();

    List<ShiftReportDTO> getShiftReportByBranchId(Long branchId);

    List<ShiftReportDTO> getShiftReportByCashierId(Long cashierId);

    ShiftReportDTO getCurrentShiftProgress(Long cashierId) throws UserException;

    ShiftReportDTO getShiftReportByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception;




}

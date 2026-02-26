package com.tap.service;


import com.tap.payload.dto.RefundDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundService {

    RefundDTO createRefund(RefundDTO refund) throws Exception;
    List<RefundDTO> getAllRefunds() throws Exception;
    List<RefundDTO> getRefundByCashier(Long cashierId) throws Exception;
    List<RefundDTO> getRefundByShiftReport(Long shiftReportId) throws Exception;
    List<RefundDTO> getRefundByCashierIdAndDateRange(Long cashier,
                                                   LocalDateTime startDate,
                                                   LocalDateTime endDate) throws Exception;
    List<RefundDTO> getRefundByBranch(Long branchId) throws Exception;

    RefundDTO getRefundByID(Long refundId) throws Exception;

    //only and super admin can delete this, store manager and store admin can't do this
    //because this is crucial for costumer
    void deleteRefund(Long refundId) throws Exception;


}

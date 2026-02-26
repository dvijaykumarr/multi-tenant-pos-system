package com.tap.mapper;

import com.tap.modal.Refund;
import com.tap.payload.dto.RefundDTO;

public class RefundMapper {

    public static RefundDTO toDTO(Refund refund){
        return RefundDTO.builder()
                .id(refund.getId())

                .orderId(refund.getOrder().getId())
                .reason(refund.getReason())
                .amount(refund.getAmount())
                .cashierName(refund.getCashier().getFullName())
                .branchId(refund.getBranch().getId())

//                .shiftReportId(refund.getShiftReport().getId())
                .shiftReportId(refund.getShiftReport() != null ? refund.getShiftReport().getId() : null) // ← fixed
                .createdAt(refund.getCreatedAt())
                .build();
    }

}

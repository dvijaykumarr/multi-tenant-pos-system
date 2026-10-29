package com.tap.payload.storeAnalytics;


import com.tap.payload.dto.BranchDTO;
import com.tap.payload.dto.ProductDTO;
import com.tap.payload.dto.RefundDTO;
import com.tap.payload.dto.UserDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StoreAlertDTO {


    private List<ProductDTO> lowStockAlerts;
    private List<BranchDTO> noSalesToday;
    private List<RefundDTO> refundSpikeAlerts;
    private List<UserDto> inactiveCashiers;
}

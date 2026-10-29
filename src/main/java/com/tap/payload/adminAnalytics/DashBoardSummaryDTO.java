package com.tap.payload.adminAnalytics;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashBoardSummaryDTO {

    private long totalStore;
    private long activeStore;
    private long blockedStore;
    private long pendingStore;

}

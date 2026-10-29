package com.tap.payload.adminAnalytics;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreRegistrationStateDTO {

    private String date;
    private Long count;
}

package com.tap.payload.storeAnalytics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class TimeSeriesPointDTO {

    private LocalDateTime date;
    private Double totalAmount;

    public TimeSeriesPointDTO(LocalDateTime date, Double totalAmount) {
        this.date = date;
        this.totalAmount = totalAmount;
    }
}

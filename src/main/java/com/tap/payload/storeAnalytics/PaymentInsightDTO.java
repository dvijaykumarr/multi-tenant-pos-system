package com.tap.payload.storeAnalytics;

import com.tap.domain.PaymentType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentInsightDTO {

    private PaymentType paymentMethod;
    private double totalAmount;
}

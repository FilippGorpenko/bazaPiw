package com.example.shop.api.payment;

import com.example.shop.api.user.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DepositSnapshot {

    @NotNull
    private Long id;

    @NotNull
    private CurrencyType currency;

    @NotNull
    private String amount;

    @NotNull
    private String depositAddress;

    @NotNull
    private Instant creationTime;

    @NotNull
    private PaymentStatus status;
}

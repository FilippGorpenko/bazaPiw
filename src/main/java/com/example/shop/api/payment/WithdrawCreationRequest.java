package com.example.shop.api.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
@Builder
@AllArgsConstructor
public class WithdrawCreationRequest {

    @NotNull
    private CurrencyType currency;

    @NotNull
    private String amount;

    @NotNull
    private String withdrawAddress;
}

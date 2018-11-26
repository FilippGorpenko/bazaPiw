package com.example.shop.api.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
@Builder
@AllArgsConstructor
public class ProductUpdateRequest {

    private String name;

    private Boolean onSale;

    private String description;

    private BigDecimal value;
}

package com.example.shop.api.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
public class ProductCreationRequest {

    @Size(min=1)
    private String name;

    @NotNull
    private Boolean onSale;

    private Long userId;

    private String description;

    @NotNull
    private BigDecimal value;
}

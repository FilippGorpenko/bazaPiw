package com.example.shop.api.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductSnapshot {

    @NotNull
    private Long id;

    @Size(min=1)
    private String name;

    @NotNull
    private Boolean onSale;

    private String description;

    @NotNull
    private BigDecimal value;
}

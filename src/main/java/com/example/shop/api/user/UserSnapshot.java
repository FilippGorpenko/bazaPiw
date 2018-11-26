package com.example.shop.api.user;

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
public class UserSnapshot {
    @NotNull
    private Long id;

    @NotNull
    private Long version;

    @Size(min = 1)
    private String username;

    private BigDecimal litecoinAmount;

    private BigDecimal moneroAmount;
}

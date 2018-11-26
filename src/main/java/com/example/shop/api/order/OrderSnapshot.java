package com.example.shop.api.order;

import com.example.shop.api.product.ProductSnapshot;
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
public class OrderSnapshot {

    @NotNull
    private Long id;

    @NotNull
    private ProductSnapshot product;

    @NotNull
    private Long userId;

    @NotNull
    private OrderStatus status;

    @NotNull
    private Instant creationTime;
}

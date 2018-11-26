package com.example.shop.mapping;

import com.example.shop.api.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

import static java.util.Objects.isNull;

@Entity
@Table(schema = "public")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    private Long userId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @NotNull
    private Instant creationTime;

    @PrePersist
    public void prePersist() {
        if (isNull(creationTime)) {
            creationTime = Instant.now();
        }
    }

}

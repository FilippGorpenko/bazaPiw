package com.example.shop.mapping;

import com.example.shop.api.payment.CurrencyType;
import com.example.shop.api.user.PaymentStatus;
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
public class Withdraw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;

    @NotNull
    private String amount;

    @NotNull
    private String withdrawAddress;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @NotNull
    private Long userId;

    @NotNull
    private Instant creationTime;

    @PrePersist
    public void prePersist() {
        if (isNull(creationTime)) {
            creationTime = Instant.now();
        }
    }
}

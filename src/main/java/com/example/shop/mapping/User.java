package com.example.shop.mapping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;


import static java.util.Objects.isNull;

@Entity
@Table(schema = "public")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    private BigDecimal litecoinAmount;

    private BigDecimal moneroAmount;

    @Version
    private Long version;

    @NotNull
    private Boolean isDeleted = false;

    @PrePersist
    public void prePersist() {
        if (isNull(isDeleted)) {
            isDeleted = false;
        }
    }
}

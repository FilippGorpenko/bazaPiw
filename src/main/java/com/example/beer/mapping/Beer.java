package com.example.beer.mapping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(schema = "public")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Beer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String tagline;

    @NotBlank
    private String firstBrewed;

    @NotBlank
    private String imageUrl;

    private Long ibu;

    @NotBlank
    private String description;

    private String foodPairing;
}

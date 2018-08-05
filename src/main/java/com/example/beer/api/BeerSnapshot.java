package com.example.beer.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Data
@Builder
public class BeerSnapshot {
    @NotNull
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

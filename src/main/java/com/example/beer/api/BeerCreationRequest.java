package com.example.beer.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@Value
@Builder
@AllArgsConstructor
public class BeerCreationRequest {

    @NotNull
    private Long punkapiId;

    @NotBlank
    private String name;

    @NotBlank
    private String tagline;

    @NotBlank
    private String firstBrewed;

    @NotBlank
    private String imageUrl;

    @NotNull
    private Long ibu;

    @NotBlank
    private String description;

    private String[] foodPairing;
}

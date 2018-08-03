package com.example.beer.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
public class BeerResponse {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String tagline;

    @NotBlank
    private String first_brewed;

    @NotBlank
    private String image_url;

    @NotNull
    private Long ibu;

    @NotBlank
    private String description;

    private String[] food_pairing;

}

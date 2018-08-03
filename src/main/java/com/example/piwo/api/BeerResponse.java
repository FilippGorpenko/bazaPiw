package com.example.piwo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@JsonIgnoreProperties( ignoreUnknown = true )
@Data
@Builder
@AllArgsConstructor
public class BeerResponse {

    @NotNull
    private String name;

    @NotNull
    private String description;

}

package com.example.piwo.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class BeerResponse {

    @NotNull
    private String name;

    @NotNull
    private String description;

}

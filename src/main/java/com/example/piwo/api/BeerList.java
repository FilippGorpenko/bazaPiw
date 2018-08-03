package com.example.piwo.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BeerList {

    private List<BeerResponse> beerLists;
}

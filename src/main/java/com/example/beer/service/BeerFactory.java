package com.example.beer.service;

import com.example.beer.api.BeerCreationRequest;
import com.example.beer.api.BeerResponse;
import com.example.beer.api.BeerSnapshot;
import com.example.beer.mapping.Beer;

import java.util.Arrays;

public class BeerFactory {


    public static Beer beerResponseToBeer(BeerResponse beerResponse) {
        return Beer.builder()
                .id(beerResponse.getId())
                .name(beerResponse.getName())
                .tagline(beerResponse.getTagline())
                .firstBrewed(beerResponse.getFirst_brewed())
                .imageUrl(beerResponse.getImage_url())
                .ibu(beerResponse.getIbu())
                .description(beerResponse.getDescription())
                .foodPairing(Arrays.toString(beerResponse.getFood_pairing()))
                .build();

    }

    public static BeerSnapshot beerToBeerSnapshot(Beer beer) {
        return BeerSnapshot.builder()
                .id(beer.getId())
                .name(beer.getName())
                .tagline(beer.getTagline())
                .firstBrewed(beer.getFirstBrewed())
                .imageUrl(beer.getImageUrl())
                .ibu(beer.getIbu())
                .description(beer.getDescription())
                .foodPairing(beer.getFoodPairing())
                .build();

    }

    public static Beer beerCreationRequestToBeer(BeerCreationRequest beerCreationRequest) {
        return Beer.builder()
                .name(beerCreationRequest.getName())
                .tagline(beerCreationRequest.getTagline())
                .firstBrewed(beerCreationRequest.getFirstBrewed())
                .imageUrl(beerCreationRequest.getImageUrl())
                .ibu(beerCreationRequest.getIbu())
                .description(beerCreationRequest.getDescription())
                .foodPairing(Arrays.toString(beerCreationRequest.getFoodPairing()))
                .build();

    }
}

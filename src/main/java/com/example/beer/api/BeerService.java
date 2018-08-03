package com.example.beer.api;

import java.util.List;

public interface BeerService {

    Long create(BeerCreationRequest beerCreationRequest);

    List<BeerSnapshot> find(String query);

}

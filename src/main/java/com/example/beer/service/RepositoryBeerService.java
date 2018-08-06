package com.example.beer.service;

import com.example.beer.repository.BeerRepository;
import com.example.beer.api.BeerCreationRequest;
import com.example.beer.api.BeerService;
import com.example.beer.api.BeerSnapshot;
import com.example.beer.mapping.Beer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RepositoryBeerService implements BeerService {

    private final BeerRepository beerRepository;

    @Override
    public Long create(BeerCreationRequest beerCreationRequest) {
        log.trace("Creating beer {request: {}}", beerCreationRequest);
        Beer beer = beerRepository.save(BeerFactory.beerCreationRequestToBeer(beerCreationRequest));
        log.info("Created beer {beer: {}}", beer);
        return beer.getId();
    }

    @Override
    public List<BeerSnapshot> find(String query) {
        log.trace("Getting beers {query: {}}", query);
        Specification<Beer> specification = Specification.where(createFoodPairingFilter(query));
        List<Beer> beers = beerRepository.findAll(specification);
        log.trace("Got beers { size: {}}", beers.size());
        return beers.stream()
                .map(BeerFactory::beerToBeerSnapshot)
                .collect(Collectors.toList());
    }

    private BeerFilter createFoodPairingFilter(String query) {
        return new BeerFilter(new FilterCriteria("foodPairing", query));

    }

}

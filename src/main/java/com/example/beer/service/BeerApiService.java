package com.example.beer.service;

import com.example.beer.Repository.BeerRepository;
import com.example.beer.api.BeerResponse;
import com.example.beer.infrastructure.BeerProperties;
import com.example.beer.mapping.Beer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.beer.service.BeerFactory.beerResponseToBeer;

@AllArgsConstructor
@Service
public class BeerApiService {

    private final RestOperations restTemplate;
    private final ObjectMapper objectMapper;
    private final BeerProperties beerProperties;
    private final BeerRepository beerRepository;

    @Transactional
    public List<BeerResponse> getAllBeers() {

        String beers = restTemplate.getForEntity(beerProperties.getApiUrl(), String.class).getBody();

        List<BeerResponse> asList;

        try {

            CollectionType javaType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, BeerResponse.class);

            asList = objectMapper.readValue(beers, javaType);

            List<Beer> beerToSave = asList.stream()
                    .map(BeerFactory::beerResponseToBeer)
                    .collect(Collectors.toList());

            beerToSave.forEach(beerRepository::save);

            return asList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


}

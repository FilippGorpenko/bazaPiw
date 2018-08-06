package com.example.beer.service;

import com.example.beer.Repository.BeerRepository;
import com.example.beer.api.BeerResponse;
import com.example.beer.infrastructure.configuration.BeerProperties;
import com.example.beer.mapping.Beer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class BeerApiService {

    private final RestOperations restTemplate;
    private final ObjectMapper objectMapper;
    private final BeerProperties beerProperties;
    private final BeerRepository beerRepository;

    @Scheduled(cron = "0 0 * ? * * *")
    public void importBeersFromPunkapi() {
        try {
            String beers = restTemplate.getForEntity(beerProperties.getApiUrl(), String.class).getBody();

            List<BeerResponse> asList = parseBeers(beers);

            List<Beer> beerToSave = asList.stream()
                    .map(BeerFactory::beerResponseToBeer)
                    .collect(Collectors.toList());

            beerToSave.forEach(beerRepository::save);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<BeerResponse> parseBeers(String beers) throws IOException {
        CollectionType javaType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, BeerResponse.class);

        return objectMapper.readValue(beers, javaType);
    }


}

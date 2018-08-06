package com.example.beer.service;

import com.example.beer.repository.BeerRepository;
import com.example.beer.api.BeerResponse;
import com.example.beer.infrastructure.configuration.BeerProperties;
import com.example.beer.mapping.Beer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class BeerApiService {

    private final RestOperations restTemplate;
    private final ObjectMapper objectMapper;
    private final BeerProperties beerProperties;
    private final BeerRepository beerRepository;

    @Scheduled(cron = "0 0 * ? * * *")
    public void importBeersFromPunkapi() {
        try {
            log.info("Starting import beers from punkApi {time: {}}", LocalDateTime.now());
            String beers = restTemplate.getForEntity(beerProperties.getApiUrl(), String.class).getBody();

            List<BeerResponse> asList = parseBeers(beers);

            List<Beer> beerToSave = asList.stream()
                    .map(BeerFactory::beerResponseToBeer)
                    .collect(Collectors.toList());

            beerToSave.forEach(beerRepository::save);
            log.info("End import beers from punkApi {time: {}, size: {}}", LocalDateTime.now(), asList.size());
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

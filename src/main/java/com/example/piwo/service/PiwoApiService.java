package com.example.piwo.service;

import com.example.piwo.api.BeerList;
import com.example.piwo.api.BeerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.List;

@AllArgsConstructor
@Service
public class PiwoApiService {

    private final RestOperations restTemplate;
    private final ObjectMapper objectMapper;


    public ResponseEntity<BeerList> getSingleBear() {
        Object[] objects = restTemplate.getForObject("https://api.punkapi.com/v2/beers/1", Object[].class);
        return null;
    }

}

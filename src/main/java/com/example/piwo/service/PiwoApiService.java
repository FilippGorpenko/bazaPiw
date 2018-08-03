package com.example.piwo.service;

import com.example.piwo.api.BeerResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import java.util.List;

@AllArgsConstructor
@Service
public class PiwoApiService {

    private final RestOperations restTemplate;

    public ResponseEntity<String> getSingleBear() {
        return restTemplate.getForEntity("https://api.punkapi.com/v2/beers/1", String.class);
    }

}

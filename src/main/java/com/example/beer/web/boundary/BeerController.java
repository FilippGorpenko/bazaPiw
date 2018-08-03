package com.example.beer.web.boundary;

import com.example.beer.api.BeerResponse;
import com.example.beer.service.BeerApiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/beer", produces = APPLICATION_JSON_UTF8_VALUE)
public class BeerController {

    private final BeerApiService piwoApiService;

    public BeerController(BeerApiService piwoApiService) {
        this.piwoApiService = piwoApiService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BeerResponse> getById() {
        return piwoApiService.getAllBeers();
    }
}

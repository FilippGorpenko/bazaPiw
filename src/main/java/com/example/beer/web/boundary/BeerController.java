package com.example.beer.web.boundary;

import com.example.beer.api.BeerCreationRequest;
import com.example.beer.api.BeerService;
import com.example.beer.api.BeerSnapshot;
import com.example.beer.service.BeerApiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/beers", produces = APPLICATION_JSON_UTF8_VALUE)
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping(value = "/foodpairings/search/{phrase} ")
    @ResponseStatus(HttpStatus.OK)
    public List<BeerSnapshot> getById(@PathVariable("phrase") String phrase) {
        return beerService.find(phrase);
    }

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@Valid @RequestBody @NotNull BeerCreationRequest beerCreationRequest) {
        return beerService.create(beerCreationRequest);
    }


}

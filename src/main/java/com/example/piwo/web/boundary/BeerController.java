package com.example.piwo.web.boundary;

import com.example.piwo.api.BeerResponse;
import com.example.piwo.service.PiwoApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/beer", produces = APPLICATION_JSON_UTF8_VALUE)
public class BeerController {

    private final PiwoApiService piwoApiService;

    public BeerController(PiwoApiService piwoApiService) {
        this.piwoApiService = piwoApiService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getById() {
        return piwoApiService.getSingleBear();
    }
}

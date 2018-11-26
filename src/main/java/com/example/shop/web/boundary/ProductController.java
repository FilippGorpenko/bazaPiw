package com.example.shop.web.boundary;

import com.example.shop.api.product.ProductCreationRequest;
import com.example.shop.api.product.ProductService;
import com.example.shop.api.product.ProductSnapshot;
import com.example.shop.api.product.ProductUpdateRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@Valid @RequestBody @NotNull ProductCreationRequest creationRequest, @AuthenticationPrincipal User user) {
        creationRequest.setUserId(Long.parseLong(user.getUsername()));
        return productService.create(creationRequest);
    }

    @GetMapping(value = "/{productId}", produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductSnapshot getById(@PathVariable("productId") Long productId) {
        return productService.getById(productId);
    }

    @PutMapping(value = "/{productId}", consumes = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("productId") Long productId, @Valid @RequestBody @NotNull ProductUpdateRequest updateRequest) {
        productService.update(productId, updateRequest);
    }

    @GetMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Page<ProductSnapshot> find() {
        return productService.find(PageRequest.of(0, 20));
    }

}

package com.example.shop.api.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface ProductService {
    Long create(@Valid @NotNull ProductCreationRequest creationRequest);

    void update(Long productId, @Valid @NotNull ProductUpdateRequest updateRequest);

    ProductSnapshot getById(Long productId);

    Page<ProductSnapshot> find(Pageable pageable);
}

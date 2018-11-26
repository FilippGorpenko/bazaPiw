package com.example.shop.service.product;

import com.example.shop.api.exception.ProductNotFoundException;
import com.example.shop.api.product.ProductCreationRequest;
import com.example.shop.api.product.ProductService;
import com.example.shop.api.product.ProductSnapshot;
import com.example.shop.api.product.ProductUpdateRequest;
import com.example.shop.mapping.Product;
import com.example.shop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static java.lang.String.format;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class RepositoryProductService implements ProductService {

    private final ProductRepository productRepository;
    private final ProductFactory productFactory;

    @Override
    public Long create(@Valid @NotNull ProductCreationRequest creationRequest) {
        log.trace("Creating product {request: {}}", creationRequest);
        Product product = productFactory.productFromCreationRequest(creationRequest);
        product = productRepository.save(product);
        log.info("Created product {user: {}}", creationRequest);
        return product.getId();
    }

    @Override
    public void update(Long productId, @Valid @NotNull ProductUpdateRequest updateRequest) {
        log.trace("Updating product {request: {}, productId: {}}", updateRequest, productId);
        Product productToUpdate = findInRepository(productId);
        productToUpdate = productFactory.mergeUpdateProductRequest(productToUpdate, updateRequest);
        productRepository.save(productToUpdate);
        log.trace("Updated product {request: {}, productId: {}}", updateRequest, productId);

    }

    @Override
    public ProductSnapshot getById(Long productId) {
        log.trace("Getting product {productId: {}}", productId);
        Product product = findInRepository(productId);
        log.trace("Got product {productId: {}}", productId);
        return productFactory.productSnapshotFromProduct(product);
    }

    @Override
    public Page<ProductSnapshot> find(Pageable pageable) {
        log.trace("Getting products {pageable: {}}", pageable);
        Page<Product> productSnapshotPage = productRepository.findAll(pageable);
        log.trace("Got products {size: {}}", productSnapshotPage.getTotalElements());
        return productSnapshotPage.map(productFactory::productSnapshotFromProduct);
    }

    private Product findInRepository(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(format("Product %d not found", productId)));
    }
}

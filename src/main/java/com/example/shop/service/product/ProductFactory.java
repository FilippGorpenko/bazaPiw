package com.example.shop.service.product;

import com.example.shop.api.product.ProductCreationRequest;
import com.example.shop.api.product.ProductSnapshot;
import com.example.shop.api.product.ProductUpdateRequest;
import com.example.shop.mapping.Product;
import com.example.shop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@AllArgsConstructor
@Component
public class ProductFactory {

    public Product productFromCreationRequest(ProductCreationRequest request) {
        return Product.builder()
                .description(request.getDescription())
                .name(request.getName())
                .onSale(request.getOnSale())
                .value(request.getValue())
                .userId(request.getUserId())
                .build();
    }

    public Product mergeUpdateProductRequest(Product product, ProductUpdateRequest productUpdateRequest) {
        if (nonNull(productUpdateRequest.getDescription())) {
            product.setDescription(productUpdateRequest.getDescription());
        }
        if (nonNull(productUpdateRequest.getName())) {
            product.setName(productUpdateRequest.getName());
        }
        if (nonNull(productUpdateRequest.getOnSale())) {
            product.setOnSale(productUpdateRequest.getOnSale());
        }
        if (nonNull(productUpdateRequest.getValue())) {
            product.setValue(productUpdateRequest.getValue());
        }

        return product;
    }

    public ProductSnapshot productSnapshotFromProduct(Product product) {
        return ProductSnapshot.builder()
                .id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .onSale(product.getOnSale())
                .value(product.getValue())
                .build();

    }
}

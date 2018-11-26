package com.example.shop.service.order;

import com.example.shop.api.order.OrderCreationRequest;
import com.example.shop.api.order.OrderSnapshot;
import com.example.shop.api.order.OrderStatus;
import com.example.shop.mapping.Order;
import com.example.shop.repository.ProductRepository;
import com.example.shop.service.product.ProductFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderFactory {

    private final ProductRepository productRepository;
    private final ProductFactory productFactory;

    public Order orderFromCreationRequest(OrderCreationRequest request) {
        return Order.builder()
                .product(productRepository.getOne(request.getProductId()))
                .userId(request.getUserId())
                .status(OrderStatus.PENDING)
                .build();
    }

    public OrderSnapshot orderToSnapshot(Order order) {
        return OrderSnapshot.builder()
                .product(productFactory.productSnapshotFromProduct(order.getProduct()))
                .userId(order.getUserId())
                .status(order.getStatus())
                .creationTime(order.getCreationTime())
                .build();
    }


}

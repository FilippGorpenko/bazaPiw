package com.example.shop.api.order;

import com.example.shop.api.product.ProductUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface OrderService {

    void create(OrderCreationRequest orderCreationRequest);

    void update(Long orderId, @Valid @NotNull ProductUpdateRequest updateRequest);

    OrderSnapshot getById(Long orderId);

    Page<OrderSnapshot> getOrderList(Pageable pageable);

}

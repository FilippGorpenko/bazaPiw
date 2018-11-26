package com.example.shop.service.order;

import com.example.shop.api.exception.OrderNotFoundException;
import com.example.shop.api.exception.ProductNotFoundException;
import com.example.shop.api.order.OrderCreationRequest;
import com.example.shop.api.order.OrderService;
import com.example.shop.api.order.OrderSnapshot;
import com.example.shop.api.product.ProductUpdateRequest;
import com.example.shop.mapping.Order;
import com.example.shop.mapping.Product;
import com.example.shop.repository.OrderRepository;
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
public class RepositoryOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;

    @Override
    public void create(OrderCreationRequest orderCreationRequest) {
        log.trace("Creating order {request: {}}", orderCreationRequest);
        Order order = orderFactory.orderFromCreationRequest(orderCreationRequest);
        orderRepository.save(order);
        log.trace("Created order {id: {}}", order.getId());

    }

    @Override
    public void update(Long orderId, @Valid @NotNull ProductUpdateRequest updateRequest) {

    }

    @Override
    public OrderSnapshot getById(Long orderId) {
        log.trace("Getting order {orderId: {}}", orderId);
        Order order = findInRepository(orderId);
        log.info("Got order {orderId: {}}", orderId);
        return orderFactory.orderToSnapshot(order);
    }

    @Override
    public Page<OrderSnapshot> getOrderList(Pageable pageable) {
        log.trace("Getting orders {pageable: {}}", pageable);
        Page<Order> orderSnapshotPage = orderRepository.findAll(pageable);
        log.trace("Got orders {size: {}}", orderSnapshotPage.getTotalElements());
        return orderSnapshotPage.map(orderFactory::orderToSnapshot);
    }

    private Order findInRepository(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(format("Order %d not found", orderId)));
    }
}

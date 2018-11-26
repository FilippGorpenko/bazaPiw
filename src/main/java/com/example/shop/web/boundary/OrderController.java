package com.example.shop.web.boundary;

import com.example.shop.api.order.OrderCreationRequest;
import com.example.shop.api.order.OrderService;
import com.example.shop.api.order.OrderSnapshot;
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
@RequestMapping(value = "/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody @NotNull OrderCreationRequest creationRequest, @AuthenticationPrincipal User user) {
        creationRequest.setUserId(Long.parseLong(user.getUsername()));
        orderService.create(creationRequest);
    }

    @GetMapping(value = "/{orderId}", produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public OrderSnapshot getById(@PathVariable("orderId") Long orderId) {
        return orderService.getById(orderId);
    }

    @GetMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Page<OrderSnapshot> find() {
        return orderService.getOrderList(PageRequest.of(0, 20));
    }
}

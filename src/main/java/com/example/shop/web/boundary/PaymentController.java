package com.example.shop.web.boundary;

import com.example.shop.api.payment.DepositCreationRequest;
import com.example.shop.api.payment.DepositSnapshot;
import com.example.shop.api.payment.WithdrawCreationRequest;
import com.example.shop.api.payment.WithdrawSnapshot;
import com.example.shop.api.payment.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/payments")
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, value = "/{userId}/deposit")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("userId") Long userId, @Valid @RequestBody @NotNull DepositCreationRequest creationRequest) {
        paymentService.depositCreate(userId, creationRequest);
    }


    @GetMapping(value = "/deposit", consumes = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Page<DepositSnapshot> findDeposits() {
        return paymentService.getDepositList(PageRequest.of(0, 20));
    }

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, value = "/{userId}/withdraw")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("userId") Long userId, @Valid @RequestBody @NotNull WithdrawCreationRequest creationRequest) {
        paymentService.withdrawCreate(userId, creationRequest);
    }


    @GetMapping(value = "/withdraw", consumes = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Page<WithdrawSnapshot> findWithdraws() {
        return paymentService.getWithdrawList(PageRequest.of(0, 20));
    }
}

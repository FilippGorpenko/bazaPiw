package com.example.shop.api.payment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {

    void depositCreate(Long userId, DepositCreationRequest depositCreationRequest);

    Page<DepositSnapshot> getDepositList(Pageable pageable);

    void withdrawCreate(Long userId, WithdrawCreationRequest withdrawCreationRequest);

    Page<WithdrawSnapshot> getWithdrawList(Pageable pageable);
}

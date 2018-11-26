package com.example.shop.service.payment;

import com.example.shop.api.payment.DepositCreationRequest;
import com.example.shop.api.payment.DepositSnapshot;
import com.example.shop.api.payment.WithdrawCreationRequest;
import com.example.shop.api.payment.WithdrawSnapshot;
import com.example.shop.api.user.PaymentStatus;
import com.example.shop.mapping.Deposit;
import com.example.shop.mapping.Withdraw;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PaymentFactory {

    public Deposit depositFromCreationRequest(DepositCreationRequest request, Long userId) {
        return Deposit.builder()
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .depositAddress(request.getDepositAddress())
                .userId(userId)
                .status(PaymentStatus.PENDING)
                .build();
    }

    public DepositSnapshot depositSnapshotFromDeposit(Deposit deposit) {
        return DepositSnapshot.builder()
                .id(deposit.getId())
                .amount(deposit.getAmount())
                .depositAddress(deposit.getDepositAddress())
                .currency(deposit.getCurrency())
                .creationTime(deposit.getCreationTime())
                .status(deposit.getStatus())
                .build();

    }

    public Withdraw withdrawFromCreationRequest(WithdrawCreationRequest request, Long userId) {
        return Withdraw.builder()
                .amount(request.getAmount())
                .currency(request.getCurrency())
                .withdrawAddress(request.getWithdrawAddress())
                .userId(userId)
                .status(PaymentStatus.PENDING)
                .build();
    }

    public WithdrawSnapshot withdrawSnapshotFromWithdraw(Withdraw withdraw) {
        return WithdrawSnapshot.builder()
                .id(withdraw.getId())
                .amount(withdraw.getAmount())
                .withdrawAddress(withdraw.getWithdrawAddress())
                .currency(withdraw.getCurrency())
                .creationTime(withdraw.getCreationTime())
                .status(withdraw.getStatus())
                .build();

    }
}

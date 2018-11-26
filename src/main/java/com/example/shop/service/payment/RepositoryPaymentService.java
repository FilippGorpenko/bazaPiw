package com.example.shop.service.payment;

import com.example.shop.api.payment.DepositCreationRequest;
import com.example.shop.api.payment.DepositSnapshot;
import com.example.shop.api.payment.WithdrawCreationRequest;
import com.example.shop.api.payment.WithdrawSnapshot;
import com.example.shop.api.payment.PaymentService;
import com.example.shop.mapping.Deposit;
import com.example.shop.mapping.Withdraw;
import com.example.shop.repository.DepositRepository;
import com.example.shop.repository.WithdrawRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class RepositoryPaymentService implements PaymentService {
    private final DepositRepository depositRepository;
    private final WithdrawRepository withdrawRepository;
    private final PaymentFactory paymentFactory;

    @Override
    public void depositCreate(Long userId, DepositCreationRequest depositCreationRequest) {
        log.trace("Creating deposit {request: {}, userId: {}}", depositCreationRequest, userId);
        Deposit deposit = paymentFactory.depositFromCreationRequest(depositCreationRequest, userId);
        depositRepository.save(deposit);
        log.info("Created deposit {deposit: {}}", deposit);
    }

    @Override
    public Page<DepositSnapshot> getDepositList(Pageable pageable) {
        log.trace("Getting deposits {pageable: {}}", pageable);
        Page<Deposit> depositPage = depositRepository.findAll(pageable);
        log.trace("Got deposits {size: {}}", depositPage.getTotalElements());
        return depositPage.map(paymentFactory::depositSnapshotFromDeposit);
    }

    @Override
    public void withdrawCreate(Long userId, WithdrawCreationRequest withdrawCreationRequest) {
        log.trace("Creating withdraw {request: {}, userId: {}}", withdrawCreationRequest, userId);
        Withdraw withdraw = paymentFactory.withdrawFromCreationRequest(withdrawCreationRequest, userId);
        withdrawRepository.save(withdraw);
        log.info("Created withdraw {withdraw: {}}", withdraw);
    }

    @Override
    public Page<WithdrawSnapshot> getWithdrawList(Pageable pageable) {
        log.trace("Getting withdraws {pageable: {}}", pageable);
        Page<Withdraw> withdrawPage = withdrawRepository.findAll(pageable);
        log.trace("Got withdraws {size: {}}", withdrawPage.getTotalElements());
        return withdrawPage.map(paymentFactory::withdrawSnapshotFromWithdraw);
    }
}

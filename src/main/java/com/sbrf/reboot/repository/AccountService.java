package com.sbrf.reboot.repository;

import lombok.NonNull;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(@NonNull AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean isClientHasContract(long clientId, long contractNumber) {
        return accountRepository.getAllAccountsByClientId(clientId).contains(contractNumber);
    }

    public boolean isContractNumberValid(long contractNumber) {
        long invalidClientId = 0L;
        return accountRepository.getClientIdByContractNumber(contractNumber) != invalidClientId;
    }
}

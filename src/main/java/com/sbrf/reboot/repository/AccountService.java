package com.sbrf.reboot.repository;

import lombok.NonNull;

public class AccountService {

    private final AccountRepository accountRepository;
    private static final long INVALID_CLIENT_ID = 0L;

    public AccountService(@NonNull AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean isClientHasContract(long clientId, long contractNumber) {
        return accountRepository.getAllAccountsByClientId(clientId).contains(contractNumber);
    }

    public boolean isContractNumberValid(long contractNumber) {
        return accountRepository.getClientIdByContractNumber(contractNumber) != INVALID_CLIENT_ID;
    }
}

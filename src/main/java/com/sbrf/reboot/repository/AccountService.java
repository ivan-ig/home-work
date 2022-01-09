package com.sbrf.reboot.repository;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AccountService {

    @NonNull
    private final AccountRepository accountRepository;

    public boolean isClientHasContract(long clientId, long contractNumber) {
        return accountRepository.getAllAccountsByClientId(clientId).contains(contractNumber);
    }

    public boolean isContractNumberValid(long contractNumber) {
        long invalidClientId = 0L;
        return accountRepository.getClientIdByContractNumber(contractNumber) != invalidClientId;
    }
}

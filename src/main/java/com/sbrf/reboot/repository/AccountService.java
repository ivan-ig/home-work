package com.sbrf.reboot.repository;

import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.io.IOException;

@AllArgsConstructor
public class AccountService {

    @NonNull
    private final AccountRepository accountRepository;

    public boolean isClientHasContract(long clientId, long contractNumber) throws IOException {
        return accountRepository.getAllAccountsByClientId(clientId).contains(contractNumber);
    }

    public boolean isContractNumberValid(long contractNumber) {
        long invalidClientId = 0L;
        return accountRepository.getClientIdByContractNumber(contractNumber) != invalidClientId;
    }
}

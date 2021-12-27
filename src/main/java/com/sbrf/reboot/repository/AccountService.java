package com.sbrf.reboot.repository;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class AccountService {

    @NonNull
    private AccountRepository accountRepository;

    public boolean isClientHasContract(long clientId, long contractNumber) {
        return accountRepository.getAllAccountsByClientId(clientId).contains(contractNumber);
    }

    public boolean isContractNumberValid(long contractNumber) {
        return accountRepository.getClientIdByContractNumber(contractNumber) != 0L;
    }
}

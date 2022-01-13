package com.sbrf.reboot.service;

import com.sbrf.reboot.repository.AccountRepository;
import com.sbrf.reboot.repository.AccountService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    AccountService accountService;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);

        accountService = new AccountService(accountRepository);
    }

    @SneakyThrows
    @Test
    void contractExist() {
        Set<Long> accounts = new HashSet<>();
        accounts.add(111L);

        long clientId = 1L;
        long contractNumber = 111L;

        when(accountRepository.getAllAccountsByClientId(clientId)).thenReturn(accounts);

        assertTrue(accountService.isClientHasContract(clientId, contractNumber));
    }

    @SneakyThrows
    @Test
    void contractNotExist() {
        Set<Long> accounts = new HashSet<>();
        accounts.add(222L);

        long clientId = 1L;
        long contractNumber = 111L;

        when(accountRepository.getAllAccountsByClientId(clientId)).thenReturn(accounts);

        assertFalse(accountService.isClientHasContract(clientId, contractNumber));
    }

    @SneakyThrows
    @Test
    void contractNumberValid() {
        long clientId = 1L;
        long contractNumber = 111L;

        when(accountRepository.getClientIdByContractNumber(contractNumber)).thenReturn(clientId);

        assertTrue(accountService.isContractNumberValid(contractNumber));
    }

    @SneakyThrows
    @Test
    void contractNumberInvalid() {
        long clientId = 0L;
        long contractNumber = 111L;

        when(accountRepository.getClientIdByContractNumber(contractNumber)).thenReturn(clientId);

        assertFalse(accountService.isContractNumberValid(contractNumber));
    }

    @Test
    void repositoryHasTreeMethods() {
        assertEquals(2, AccountRepository.class.getMethods().length);
    }

    @Test
    void serviceHasTreeMethods() {
        assertEquals(2, AccountService.class.getMethods().length - Object.class.getMethods().length);
    }

}
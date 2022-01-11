package com.sbrf.reboot.repository;

import com.sbrf.reboot.repository.impl.FileAccountRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileAccountRepositoryTest {

    AccountRepository accountRepository;

    @Test
    void onlyPersonalAccounts() {
        String filePath = "src/main/resources/Accounts.txt";
        accountRepository = new FileAccountRepository(filePath);

        long clientId = 1L;
        Set<Long> actualAccounts = accountRepository.getAllAccountsByClientId(clientId);

        Set<Long> expected = new HashSet<Long>() {{
            add(111L);
            add(222L);
            add(333L);
        }};

        assertEquals(expected, actualAccounts);
    }

    @Test
    void failGetAllAccountsByClientId() {
        long clientId = 1L;
        String filePath = "somePath";
        accountRepository = new FileAccountRepository(filePath);
        Set<Long> actualAccounts = accountRepository.getAllAccountsByClientId(clientId);

        assertTrue(actualAccounts.isEmpty());
    }

    @Test
    void updatingAbsentAccountNumber() throws IOException {
        long clientId = 1L, oldNumber = 3L, newNumber = 555L;
        String filePath = "src/main/resources/Accounts.txt";
        FileAccountRepository far = new FileAccountRepository(filePath);
        far.updateAccountNumber(clientId, oldNumber, newNumber);

        Set<Long> actualAccounts = far.getAllAccountsByClientId(1L);
        Set<Long> expected = new HashSet<Long>() {{
            add(111L);
            add(333L);
            add(222L);
        }};

        actualAccounts.forEach(e -> assertTrue(expected.contains(e)));
    }

    @Test
    void successfulUpdatingAccountNumber() throws IOException {
        long clientId = 2L, oldNumber = 777L, newNumber = 123123L;
        String filePath = "src/main/resources/Accounts.txt";
        FileAccountRepository far = new FileAccountRepository(filePath);
        far.updateAccountNumber(clientId, oldNumber, newNumber);

        Set<Long> actualAccounts = far.getAllAccountsByClientId(2L);
        Set<Long> expected = new HashSet<Long>() {{
            add(123123L);
        }};

        actualAccounts.forEach(e -> assertTrue(expected.contains(e)));
    }
}
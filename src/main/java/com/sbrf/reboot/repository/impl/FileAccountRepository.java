package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.String.valueOf;

@RequiredArgsConstructor
public class FileAccountRepository implements AccountRepository {

    @NonNull
    private final String filePath;
    private final List<String> fileLines = new ArrayList<>();

    @Override
    public Set<Long> getAllAccountsByClientId(long clientId) {
        readLinesFromFileToList();
        return parseListLinesAndGetAccountNumbersByClientId(clientId);
    }

    private void readLinesFromFileToList() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while (bufferedReader.ready()) {
                fileLines.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Set<Long> parseListLinesAndGetAccountNumbersByClientId(long clientId) {
        String clientIdValue = "";
        Set<Long> accountNumbers = new HashSet<>();

        for (String line : fileLines) {
            if (line.contains("clientId")) {
                String[] clientIdLine = line.split(" ");
                clientIdValue = clientIdLine[1].replace(',', ' ').trim();
            }
            if (clientIdValue.equals(valueOf(clientId)) && line.contains("number")) {
                String[] lineWithNumber = line.split(" ");
                try {
                    Long number = Long.parseLong(lineWithNumber[1]);
                    accountNumbers.add(number);
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
            }
        }
        return accountNumbers;
    }

    @Override
    public long getClientIdByContractNumber(long contractNumber) {
        throw new RuntimeException("Method is not implemented");
    }
}

package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.util.*;

import static java.lang.String.valueOf;

@RequiredArgsConstructor
public class FileAccountRepository implements AccountRepository {

    @NonNull
    private final String filePath;

    @Override
    public Set<Long> getAllAccountsByClientId(long clientId) {
        List<String> fileLines = readLinesFromFileToList();
        return parseLinesAndGetAccountNumbersByClientId(fileLines, clientId);
    }

    private List<String> readLinesFromFileToList() {
        List<String> listOfLines = new ArrayList<>(23);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while (bufferedReader.ready()) {
                listOfLines.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfLines;
    }

    private Set<Long> parseLinesAndGetAccountNumbersByClientId(List<String> fileLines, long clientId) {
        String clientIdValue = "";
        Set<Long> accountNumbers = new HashSet<>();

        for (String line : fileLines) {
            if (line.contains("clientId")) {
                String[] clientIdLine = line.split(" ");
                clientIdValue = clientIdLine[clientIdLine.length - 1].replace(',', ' ').trim();
            }
            if (clientIdValue.equals(valueOf(clientId)) && line.contains("number")) {
                String[] lineWithNumber = line.split(" ");
                try {
                    Long number = Long.parseLong(lineWithNumber[lineWithNumber.length - 1]);
                    accountNumbers.add(number);
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }
            }
        }
        return accountNumbers;
    }

    public void updateAccountNumber(long clientId, long oldNumber, long newNumber) throws IOException {
        List<String> linesForWriting = prepareListLinesForWriting(clientId, oldNumber, newNumber);
        if (!linesForWriting.isEmpty()) {
            rewriteFile(linesForWriting);
        }
    }

    private List<String> prepareListLinesForWriting(long clientId, long oldNumber, long newNumber) {
        List<String> fileLines = readLinesFromFileToList();
        String clientIdLinePattern = "\"clientId\": " + clientId + ",";
        String oldNumberPattern = "    \"number\": " + oldNumber;
        String newNumberPattern = "    \"number\": " + newNumber;

        for (int i = 0; i < fileLines.size(); i++){
            if (fileLines.get(i).contains(clientIdLinePattern)) {
                i++;
            }
            if (fileLines.get(i).equals(oldNumberPattern)) {
                fileLines.set(i, newNumberPattern);
            }
        }
        return fileLines;
    }

    private void rewriteFile(List<String> linesForWriting) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : linesForWriting) {
                bufferedWriter.write(line+"\n");
            }
        }
    }

    @Override
    public long getClientIdByContractNumber(long contractNumber) {
        throw new RuntimeException("Method is not implemented");
    }
}

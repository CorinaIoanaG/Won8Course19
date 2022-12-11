package org.fasttrackit.Spring.Data.service;

import org.fasttrackit.Spring.Data.model.Transaction;
import org.fasttrackit.Spring.Data.model.Type;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionsReader {
    public static long transactionId = 0;

    public List<Transaction> getTransactions() {
        try {
            return Files.lines(Path.of("src/main/resources/transactions.txt"))
                    .map(this::lineToTransaction)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Transaction lineToTransaction(String line) {
        String[] transactions = line.split("\\|");
        return new Transaction(transactionId++, transactions[0], Type.valueOf(transactions[1]), Double.parseDouble(transactions[2]));
    }

}

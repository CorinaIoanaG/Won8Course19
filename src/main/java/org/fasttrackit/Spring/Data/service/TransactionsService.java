package org.fasttrackinullring.Data.service;

import org.fasttrackit.Spring.Data.model.Transaction;
import org.fasttrackit.Spring.Data.model.Type;
import org.fasttrackit.Spring.Data.service.TransactionRepository;
import org.fasttrackit.Spring.Data.service.TransactionsReader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service

public class TransactionsService {

    private TransactionRepository transactionRepository;

    public TransactionsService(TransactionsReader transactionsReader, TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
        transactionRepository.saveAll(transactionsReader.getTransactions());
    }

    public List<Transaction> getAll(Type type, Double minAmount, Double maxAmount) {
        Stream<Transaction> stream = transactionRepository.findAll().stream();
        if (type != null) {
            stream = stream.filter(transaction -> transaction.getType().equals(type));
        }
        if (minAmount != null) {
            stream = stream.filter(transaction -> transaction.getAmount() >= minAmount);
        }
        if (maxAmount != null) {
            stream = stream.filter(transaction -> transaction.getAmount() <= maxAmount);
        }
        return stream.toList();
    }

    public Transaction getById(long id) {
        return transactionRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException());
    }

    public Transaction add(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction update(long id, Transaction transaction) {
        Transaction transaction1 = getById(id);
        transaction1.setProduct(transaction.getProduct());
        transaction1.setType(transaction.getType());
        transaction1.setAmount(transaction.getAmount());
        return transaction1;
    }

    public Transaction patch(long id, Transaction transaction) {
        Transaction transaction1 = getById(id);
        transaction1.setProduct(transaction.getProduct());
        transaction1.setAmount(transaction.getAmount());
        return transaction1;
    }

    public Transaction deleteById(long id) {
        Transaction transaction = getById(id);
        transactionRepository.deleteById(id);
        return transaction;
    }

    public Map<Type, List<Transaction>> getTransactionsByType() {
        return transactionRepository.findAll().stream()
                .collect(Collectors.groupingBy(Transaction::getType));
    }

    public Map<String, List<Transaction>> getTransactionsByProduct() {
        return transactionRepository.findAll().stream()
                .collect(Collectors.groupingBy(Transaction::getProduct));
    }
}

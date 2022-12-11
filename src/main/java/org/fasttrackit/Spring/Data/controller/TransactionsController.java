package org.fasttrackit.Spring.Data.controller;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.Spring.Data.model.Transaction;
import org.fasttrackit.Spring.Data.model.Type;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("transactions") // http://host:port/transactions

public class TransactionsController {
    private org.fasttrackinullring.Data.service.TransactionsService transactionsService;

    @GetMapping
    public List<Transaction> getAll(@RequestParam(required = false) Type type, Double minAmount, Double maxAmount) {
        return transactionsService.getAll(type, minAmount, maxAmount);
    }

    @GetMapping(value = "{id}")
    public Transaction getById(@PathVariable int id) {
        return transactionsService.getById(id);
    }

    @PostMapping
    public Transaction add(@RequestBody Transaction transaction) {
        return transactionsService.add(transaction);
    }

    @PutMapping(value = "{id}")
    public Transaction update(@PathVariable int id, @RequestBody Transaction transaction) {
        return transactionsService.update(id, transaction);
    }

    @PatchMapping(value = "{id}")
    public Transaction patch(@PathVariable int id, @RequestBody Transaction transaction) {
        return transactionsService.patch(id, transaction);
    }

    @DeleteMapping(value = "{id}")
    public Transaction deleteById(@PathVariable int id) {
        return transactionsService.deleteById(id);
    }

    @GetMapping(value = "/type-group")
    public Map<Type, List<Transaction>> getMapByType() {
        return transactionsService.getTransactionsByType();
    }

    @GetMapping(value = "/product-group")
    public Map<String, List<Transaction>> getMapByProduct() {
        return transactionsService.getTransactionsByProduct();
    }

}

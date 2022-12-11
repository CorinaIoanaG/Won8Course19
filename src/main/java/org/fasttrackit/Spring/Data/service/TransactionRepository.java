package org.fasttrackit.Spring.Data.service;

import org.fasttrackit.Spring.Data.model.Transaction;
import org.fasttrackit.Spring.Data.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}

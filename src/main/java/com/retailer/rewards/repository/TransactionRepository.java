package com.retailer.rewards.repository;

import com.retailer.rewards.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions,Long> {}

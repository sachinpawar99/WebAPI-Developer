package com.retailer.rewards.repository;

import com.retailer.rewards.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing and managing {@link Transactions} entities.
 * This interface extends JpaRepository, providing methods for CRUD operations
 * and additional querying capabilities on Transactions data.
 */
public interface TransactionRepository extends JpaRepository<Transactions,Long> {}

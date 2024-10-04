package com.retailer.rewards.services;

import com.retailer.rewards.dto.TransactionDto;
import com.retailer.rewards.entities.Transactions;

/**
 * Service interface for managing transaction-related operations.
 * This interface defines methods for adding transactions
 */
public interface TransactionService {
    /**
     * Adds a new Transactions to the system.
     *
     * @param transactionDto the customer object to be added
     * @return the added transactions object
     */
    public Transactions addTransactions(TransactionDto transactionDto);
}

package com.retailer.rewards.services;

import com.retailer.rewards.dto.TransactionDto;
import com.retailer.rewards.entities.Transactions;

public interface TransactionService {
    public Transactions addTransactions(TransactionDto transactionDto);
}

package com.retailer.rewards.services.impl;

import com.retailer.rewards.dto.TransactionDto;
import com.retailer.rewards.entities.Transactions;
import com.retailer.rewards.exceptions.BadRequestException;
import com.retailer.rewards.exceptions.ResourceNotFoundException;
import com.retailer.rewards.repository.CustomerRepository;
import com.retailer.rewards.repository.TransactionRepository;
import com.retailer.rewards.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the TransactionService interface.
 * This class provides the business logic for managing transactions,
 * including adding transactions.
 */
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
    @Override
    public Transactions addTransactions(TransactionDto transactionDto) {

        LOGGER.info("Adding new transaction");
        Transactions transactions = new Transactions();
        transactions.setTransactionsAmount(transactionDto.getTransactionAmount());
        transactions.setCustomer(customerRepository.findById(transactionDto.getCustomerId())
                .orElseThrow(()->new ResourceNotFoundException("CUSTOMER_ID","NOT_FOUND",transactionDto.getCustomerId())));
        if (transactions.getTransactionsAmount() <= 0) {
            throw new BadRequestException("Transaction amount" ,"must be greater than 0",transactionDto.getTransactionAmount());
        }
       return transactionRepository.save(transactions);

    }

}

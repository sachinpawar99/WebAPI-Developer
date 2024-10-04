package com.retailer.rewards.controllers;

import com.retailer.rewards.dto.TransactionDto;
import com.retailer.rewards.entities.Transactions;
import com.retailer.rewards.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling transaction-related operations, such as adding a transaction.
 */
@RestController
@RequestMapping("/api/transaction/")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
    /**
     * Adds a new transaction to the system.
     *
     * @param transactionsDto the transaction data transfer object to be added
     * @return ResponseEntity containing the added transaction object and HTTP status code CREATED
     */
    @PostMapping
    public ResponseEntity<Transactions> addTransaction(@RequestBody TransactionDto transactionsDto)
    {
        LOGGER.info("Request to add a new transaction");
        ResponseEntity<Transactions> response = new ResponseEntity<>(transactionService.addTransactions(transactionsDto), HttpStatus.CREATED);
        LOGGER.info("Transaction added successfully");
        return response;
    }
}

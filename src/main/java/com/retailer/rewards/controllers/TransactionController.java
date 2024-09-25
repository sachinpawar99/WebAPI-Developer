package com.retailer.rewards.controllers;

import com.retailer.rewards.dto.TransactionDto;
import com.retailer.rewards.entities.Transactions;
import com.retailer.rewards.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction/")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping
    public ResponseEntity<Transactions> addTransaction(@RequestBody TransactionDto transactionsDto)
    {
        return new ResponseEntity<>(transactionService.addTransactions(transactionsDto), HttpStatus.CREATED);
    }
}

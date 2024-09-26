package com.retailer.rewards.services.impl;

import com.retailer.rewards.dto.TransactionDto;
import com.retailer.rewards.entities.Transactions;
import com.retailer.rewards.exceptions.BadRequestException;
import com.retailer.rewards.exceptions.ResourceNotFoundException;
import com.retailer.rewards.repository.CustomerRepository;
import com.retailer.rewards.repository.TransactionRepository;
import com.retailer.rewards.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Transactions addTransactions(TransactionDto transactionDto) {

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

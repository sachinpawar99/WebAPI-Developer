package com.retailer.rewards.services.impl;

import com.retailer.rewards.dto.TransactionDto;
import com.retailer.rewards.entities.Transactions;
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
                .orElseThrow(()->new ResourceNotFoundException("Customer_ID not found","code",transactionDto.getCustomerId())));

       return transactionRepository.save(transactions);

    }

}

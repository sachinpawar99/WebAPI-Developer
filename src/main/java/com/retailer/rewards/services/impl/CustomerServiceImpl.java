package com.retailer.rewards.services.impl;

import com.retailer.rewards.entities.Customer;
import com.retailer.rewards.entities.Transactions;
import com.retailer.rewards.exceptions.ResourceNotFoundException;
import com.retailer.rewards.repository.CustomerRepository;
import com.retailer.rewards.services.CustomerService;
import com.retailer.rewards.utility.CalculatingRewardsPoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the CustomerService interface.
 * This class provides the business logic for managing customers,
 * including adding customers and retrieving rewards points information.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CalculatingRewardsPoints calculatingRewardsPoints;
    @Autowired
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Override
    public Customer addCustomer(Customer customer) {
        LOGGER.info("Adding new customer: {}", customer);
        return customerRepository.save(customer);
    }
    @Override
    public Map<String,Object> getRewardsPointsByCustomerId(Long customerId) {

        LOGGER.info("Retrieving rewards points for customer ID: {}", customerId);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->
                {   LOGGER.error("Customer id not found {} : ",customerId);
                    throw new ResourceNotFoundException("CUSTOMER_ID","NOT_FOUND",customerId);
                });
       List<Transactions> transactions = customer.getTransactionsList();

        Map<String, Object> totalRewardsPoints = calculatingRewardsPoints.getTotalRewardsPoints(transactions);

        totalRewardsPoints.put("customerId ",customer.getCustomerId());

        return totalRewardsPoints;
    }

    public List<Map<String, Object>> getAllCustomersWithRewards() {

        LOGGER.info("Retrieving all customers with rewards");
        List<Customer> customers = customerRepository.findAll();

        List<Map<String, Object>> customersWithRewards = new ArrayList<>();

        for (Customer customer : customers) {
            Map<String, Object> customerData = new HashMap<>();
            customerData.put("customerId", customer.getCustomerId());
            customerData.put("customerName", customer.getCustomerName());

            // Fetch customer's transactions
            List<Transactions> transactions = customer.getTransactionsList();

            //Calculate rewards points for this customer
            Map<String, Object> rewardsPoints = calculatingRewardsPoints.getTotalRewardsPoints(transactions);

            customerData.put("rewardsPoints", rewardsPoints);
            customerData.put("transactionsList", transactions);

            customersWithRewards.add(customerData);
        }

       return customersWithRewards;
    }
}

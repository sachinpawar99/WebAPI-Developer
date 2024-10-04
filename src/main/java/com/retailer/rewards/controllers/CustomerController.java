package com.retailer.rewards.controllers;

import com.retailer.rewards.entities.Customer;
import com.retailer.rewards.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for handling customer-related operations, such as adding a customer and retrieving reward points.
 */
@RestController
@RequestMapping("/api/customer/rewards/")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    /**
     * Adds a new customer to the system.
     *
     * @param customer the customer object to be added
     * @return ResponseEntity containing the added customer object and HTTP status code CREATED
     */
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        LOGGER.info("Request to add a new customer ");
        ResponseEntity<Customer> response = new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
        LOGGER.info("Customer added successfully with customerId: {} ",customer.getCustomerId());
        return response;
    }

    /**
     * Retrieves the reward points for a specific customer by their ID.
     *
     * @param customerId the ID of the customer whose reward points are to be fetched
     * @return ResponseEntity containing a map of the customer's reward points and HTTP status code OK
     */
    @GetMapping("{customerId}")
    public ResponseEntity<Map<String,Object>> getRewardsPointsByCustomerId(@PathVariable("customerId") long customerId)
    {
        LOGGER.info("Fetching reward points for customer Id: {}", customerId);
        Map<String,Object> getRewardPoints = customerService.getRewardsPointsByCustomerId(customerId);
        LOGGER.info("Reward points retrieved for customer Id: {}", customerId);
        return new ResponseEntity<>(getRewardPoints,HttpStatus.OK);
    }

    /**
     * Retrieves the reward points of all customers.
     *
     * @return ResponseEntity containing a list of maps with customer details and reward points, and HTTP status code OK
     */
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllCustomerRewards() {
        LOGGER.info("Fetching reward points for all customers");
        List<Map<String, Object>> customersWithRewardsPoints = customerService.getAllCustomersWithRewards();
        LOGGER.info("Successfully retrieved reward points for all customers");
        return new ResponseEntity<>(customersWithRewardsPoints,HttpStatus.OK);
    }
}

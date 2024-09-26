package com.retailer.rewards.controllers;

import com.retailer.rewards.entities.Customer;
import com.retailer.rewards.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customer/rewards/")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
    }
    @GetMapping("{customerId}")
    public ResponseEntity<Map<String,Object>> getRewardsPointsByCustomerId(@PathVariable("customerId") long customerId)
    {
        Map<String,Object> getRewardPoints = customerService.getRewardsPointsByCustomerId(customerId);

        return new ResponseEntity<>(getRewardPoints,HttpStatus.OK);
    }

    // for this if you want see all customer details so use this.
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllCustomerRewards() {
        List<Map<String, Object>> customersWithRewardsPoints = customerService.getAllCustomersWithRewards();
        return new ResponseEntity<>(customersWithRewardsPoints,HttpStatus.OK);
    }
}

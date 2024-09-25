package com.retailer.rewards.controllers;

import com.retailer.rewards.entities.Customer;
import com.retailer.rewards.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Page<Map<String, Object>>> getAllCustomerRewards(Pageable pageable) {
        Page<Map<String, Object>> customersWithRewardsPoints = customerService.getAllCustomersWithRewards(pageable);
        return new ResponseEntity<>(customersWithRewardsPoints,HttpStatus.OK);
    }
}

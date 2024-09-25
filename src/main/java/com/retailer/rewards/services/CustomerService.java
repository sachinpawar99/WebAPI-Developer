package com.retailer.rewards.services;

import com.retailer.rewards.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CustomerService {

    public Customer addCustomer(Customer customer);
    public Map<String,Object> getRewardsPointsByCustomerId(Long customerId);
    public Page<Map<String, Object>> getAllCustomersWithRewards(Pageable pageable);
}

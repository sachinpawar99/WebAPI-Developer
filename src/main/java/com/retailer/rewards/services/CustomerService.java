package com.retailer.rewards.services;

import com.retailer.rewards.entities.Customer;
import java.util.List;
import java.util.Map;

public interface CustomerService {

    public Customer addCustomer(Customer customer);
    public Map<String,Object> getRewardsPointsByCustomerId(Long customerId);
    public List<Map<String, Object>> getAllCustomersWithRewards();
}

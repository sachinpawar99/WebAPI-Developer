package com.retailer.rewards.services.impl;

import com.retailer.rewards.entities.Customer;
import com.retailer.rewards.entities.Transactions;
import com.retailer.rewards.exceptions.ResourceNotFoundException;
import com.retailer.rewards.repository.CustomerRepository;
import com.retailer.rewards.repository.TransactionRepository;
import com.retailer.rewards.services.CustomerService;
import com.retailer.rewards.utility.CalculatingRewardsPoints;
import com.retailer.rewards.utility.CustomerPagedResponseAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CalculatingRewardsPoints calculatingRewardsPoints;
    @Autowired
    private CustomerPagedResponseAssembler customerPagedResponseAssembler;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Map<String,Object> getRewardsPointsByCustomerId(Long customerId) {

        Customer customer = customerRepository.findById(customerId).get();
        if(customer==null)
        {
            throw new ResourceNotFoundException("Customer_ID not found","code",customerId);

        }

       List<Transactions> transactions = customer.getTransactionsList();

        Map<String, Object> totalRewardsPoints = calculatingRewardsPoints.getTotalRewardsPoints(transactions);

        totalRewardsPoints.put("customerId ",customer.getCustomerId());

        return totalRewardsPoints;
    }

    // To recommend for directly access details now showing paging url here

//    public Page<Map<String, Object>> getAllCustomersWithRewards(Pageable pageable) {
//
//        Page<Customer> customers = customerRepository.findAll(pageable);
//
//        List<Map<String, Object>> customersWithRewards = new ArrayList<>();
//
//        for (Customer customer : customers) {
//            Map<String, Object> customerData = new HashMap<>();
//            customerData.put("customerId", customer.getCustomerId());
//            customerData.put("customerName", customer.getCustomerName());
//
//            // Fetch customer's transactions
//            List<Transactions> transactions = customer.getTransactionsList();
//
//            //Calculate rewards points for this customer
//            Map<String, Object> rewardsPoints = calculatingRewardsPoints.getTotalRewardsPoints(transactions);
//
//            customerData.put("rewardsPoints", rewardsPoints);
//            customerData.put("transactionsList", transactions);
//
//            customersWithRewards.add(customerData);
//        }
//
//        return new PageImpl<>(customersWithRewards, pageable, customers.getTotalElements());
//    }

    public Page<Map<String, Object>> getAllCustomersWithRewards(Pageable pageable) {
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        return customerPagedResponseAssembler.toPagedModel(customerPage);
    }

}

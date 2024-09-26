package com.retailer.rewards.repository;

import com.retailer.rewards.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> { }

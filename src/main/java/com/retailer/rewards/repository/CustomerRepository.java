package com.retailer.rewards.repository;

import com.retailer.rewards.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for accessing and managing {@link Customer} entities.
 * This interface extends JpaRepository, providing methods for CRUD operations
 * and additional querying capabilities on Customer data.
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> { }

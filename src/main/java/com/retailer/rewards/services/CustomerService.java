package com.retailer.rewards.services;

import com.retailer.rewards.entities.Customer;
import java.util.List;
import java.util.Map;

/**
 * Service interface for managing customer-related operations.
 * This interface defines methods for adding customers and retrieving
 * rewards points associated with customers.
 */
public interface CustomerService {

    /**
     * Adds a new customer to the system.
     *
     * @param customer the customer object to be added
     * @return the added customer object
     */
    public Customer addCustomer(Customer customer);
    /**
     * Retrieves the rewards points for a specific customer by their ID.
     *
     * @param customerId the ID of the customer whose rewards points are to be retrieved
     * @return a map containing the rewards points and any related information
     */
    public Map<String,Object> getRewardsPointsByCustomerId(Long customerId);
    /**
     * Retrieves all customers along with their rewards points.
     *
     * @return a list of maps, each containing customer details and their rewards points
     */
    public List<Map<String, Object>> getAllCustomersWithRewards();
}

package com.retailer.rewards.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.retailer.rewards.controllers.CustomerController;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CustomerController} class.
 * This class verifies the behavior of customer-related endpoints,
 * including adding a customer, retrieving rewards points, and listing all customers.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    /**
     * Set up method that is run before each test case.
     * This method can be used to initialize common resources needed for the tests.
     */
    @BeforeEach
    public void setUp() {
        // Any setup needed before each test can be added here
    }

    /**
     * Test for adding a new customer.
     * This test verifies that the API correctly adds a customer and returns a CREATED status.
     *
     * @throws Exception if an error occurs during the request execution
     */
    @Test
    public void testAddCustomer() throws Exception {
        String customerJson = "{\"customerName\": \"jacob jerry\"}";

        mockMvc.perform(post("/api/customer/rewards/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andExpect(status().isCreated());
    }

    /**
     * Test for retrieving rewards points by customer ID.
     * This test checks that the API correctly retrieves rewards points for a specific customer.
     *
     * @throws Exception if an error occurs during the request execution
     */
    @Test
    public void testGetRewardsPointsByCustomerId() throws Exception {
        long customerId = 1; // Use a valid customer ID

        mockMvc.perform(get("/api/customer/rewards/{customerId}", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPoints").exists());
    }

    /**
     * Test for retrieving all customers with rewards.
     * This test verifies that the API correctly returns a list of all customers with their rewards.
     *
     * @throws Exception if an error occurs during the request execution
     */
    @Test
    public void testGetAllCustomerRewards() throws Exception {
        mockMvc.perform(get("/api/customer/rewards/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
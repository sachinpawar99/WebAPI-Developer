package com.retailer.rewards.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.retailer.rewards.controllers.TransactionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Integration tests for the {@link TransactionController} class.
 * This class verifies the behavior of transaction-related endpoints,
 * specifically adding transactions.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerIntegrationTest {
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
     * Test for adding a new transaction.
     * This test verifies that the API correctly adds a transaction and returns a CREATED status.
     *
     * @throws Exception if an error occurs during the request execution
     */
    @Test
    public void testAddTransaction() throws Exception {
        String transactionJson = "{\"customerId\": 1, \"transactionAmount\": 100.0}";

        mockMvc.perform(post("/api/transaction/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transactionJson))
                .andExpect(status().isCreated());
    }
}

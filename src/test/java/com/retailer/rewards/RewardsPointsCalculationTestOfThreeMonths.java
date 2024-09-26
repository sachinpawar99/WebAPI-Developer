package com.retailer.rewards;

import com.retailer.rewards.entities.Customer;
import com.retailer.rewards.entities.Transactions;
import com.retailer.rewards.repository.CustomerRepository;
import com.retailer.rewards.services.impl.CustomerServiceImpl;
import com.retailer.rewards.utility.CalculatingRewardsPoints;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RewardsPointsCalculationTestOfThreeMonths {
        @Mock
        private CustomerRepository customerRepository;
        @Mock
        private CalculatingRewardsPoints calculatingRewardsPoints;
        @InjectMocks
        private CustomerServiceImpl customerService;
        private Customer customer;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);

            customer = new Customer();
            customer.setCustomerId(1L);
            customer.setCustomerName("Bob Smith");
            when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        }

        // Test Case 1: Single Transaction in the Last Month
        @Test
        public void testSingleTransactionInLastMonth() {
            Transactions transaction = new Transactions(1L, 250.0, LocalDate.now().minusMonths(1), customer);
            customer.setTransactionsList(Arrays.asList(transaction));

            // Expected points calculation
            Map<String, Object> expectedPoints = new HashMap<>();
            expectedPoints.put("totalPoints", 350);
            expectedPoints.put("monthlyPoints", Map.of("September", 350));

            when(calculatingRewardsPoints.getTotalRewardsPoints(customer.getTransactionsList()))
                    .thenReturn(expectedPoints);

            Map<String, Object> result = customerService.getRewardsPointsByCustomerId(customer.getCustomerId());

            assertEquals(expectedPoints, result);
        }

        // Test Case 2: Multiple Transactions in the Same Month Testing
        @Test
        public void testMultipleTransactionsSameMonth() {
            Transactions transaction1 = new Transactions(2L, 120.0, LocalDate.now().minusMonths(2), customer);
            Transactions transaction2 = new Transactions(3L, 180.0, LocalDate.now().minusMonths(2), customer);
            customer.setTransactionsList(Arrays.asList(transaction1, transaction2));

            // Expected points calculation
            Map<String, Object> expectedPoints = new HashMap<>();
            expectedPoints.put("totalPoints", 300);
            expectedPoints.put("monthlyPoints", Map.of("August", 300));

            when(calculatingRewardsPoints.getTotalRewardsPoints(customer.getTransactionsList()))
                    .thenReturn(expectedPoints);

            Map<String, Object> result = customerService.getRewardsPointsByCustomerId(customer.getCustomerId());

            assertEquals(expectedPoints, result);
        }

        // Test Case 3: Transactions Of Three Months Testing
        @Test
        public void testTransactionsSpreadAcrossThreeMonths() {
            Transactions transaction1 = new Transactions(4L, 90.0, LocalDate.now().minusMonths(1), customer);
            Transactions transaction2 = new Transactions(5L, 150.0, LocalDate.now().minusMonths(2), customer);
            Transactions transaction3 = new Transactions(6L, 60.0, LocalDate.now().minusMonths(3), customer);
            customer.setTransactionsList(Arrays.asList(transaction1, transaction2, transaction3));

            // Expected points calculation
            Map<String, Object> expectedPoints = new HashMap<>();
            expectedPoints.put("totalPoints", 200);
            expectedPoints.put("monthlyPoints", Map.of("September", 40, "August", 150, "July", 10));

            when(calculatingRewardsPoints.getTotalRewardsPoints(customer.getTransactionsList()))
                    .thenReturn(expectedPoints);

            Map<String, Object> result = customerService.getRewardsPointsByCustomerId(customer.getCustomerId());

            assertEquals(expectedPoints, result);
        }

        // Test Case 4: No Transactions in the Last Three Months Testing
        @Test
        public void testNoTransactionsInLastThreeMonths() {
            customer.setTransactionsList(Arrays.asList());

            // Expected points calculation
            Map<String, Object> expectedPoints = new HashMap<>();
            expectedPoints.put("totalPoints", 0);
            expectedPoints.put("monthlyPoints", Map.of());

            when(calculatingRewardsPoints.getTotalRewardsPoints(customer.getTransactionsList()))
                    .thenReturn(expectedPoints);

            Map<String, Object> result = customerService.getRewardsPointsByCustomerId(customer.getCustomerId());

            assertEquals(expectedPoints, result);
        }

        // Test Case 5: Mix of Transactions in and Outside the Last Three Months
        @Test
        public void testMixOfTransactionsInAndOutsideLastThreeMonths() {
            Transactions transaction1 = new Transactions(7L, 110.0, LocalDate.now().minusMonths(2), customer);
            Transactions transaction2 = new Transactions(8L, 90.0, LocalDate.now().minusMonths(5), customer);
            customer.setTransactionsList(Arrays.asList(transaction1, transaction2));

            // Expected points calculation (ignore the transaction outside last three months)
            Map<String, Object> expectedPoints = new HashMap<>();
            expectedPoints.put("totalPoints", 70);
            expectedPoints.put("monthlyPoints", Map.of("August", 70));

            when(calculatingRewardsPoints.getTotalRewardsPoints(customer.getTransactionsList()))
                    .thenReturn(expectedPoints);

            Map<String, Object> result = customerService.getRewardsPointsByCustomerId(customer.getCustomerId());

            assertEquals(expectedPoints, result);
        }
}
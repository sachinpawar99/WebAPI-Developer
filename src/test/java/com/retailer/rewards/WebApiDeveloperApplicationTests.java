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
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link WebApiDeveloperApplication} application.
 * This class contains test methods to verify the functionality of the web API,
 * specifically related to customer rewards management.
 */
@SpringBootTest
class WebApiDeveloperApplicationTests {
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private CalculatingRewardsPoints calculatingRewardsPoints;
	@InjectMocks
	private CustomerServiceImpl customerService;
	private List<Customer> customerList;

	/**
	 * Set up method that is run before each test case.
	 * This method can be used to initialize common resources needed for the tests.
	 */
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		customerList = new ArrayList<>();

		// Create customers
		Customer customer1 = new Customer();
		customer1.setCustomerId(1L);
		customer1.setCustomerName("Sachin Pawar");

		Transactions t1 = new Transactions(1L, 120.5, LocalDate.of(2024, 7, 15), customer1);
		Transactions t2 = new Transactions(2L, 200.0, LocalDate.of(2024, 7, 18), customer1);
		customer1.setTransactionsList(Arrays.asList(t1, t2));
//==================================
		Customer customer2 = new Customer();
		customer2.setCustomerId(2L);
		customer2.setCustomerName("Bob Smith");

		Transactions t3 = new Transactions(3L, 75.0, LocalDate.of(2024, 8, 5), customer2);
		Transactions t4 = new Transactions(4L, 150.75, LocalDate.of(2024, 9, 10), customer2);
		customer2.setTransactionsList(Arrays.asList(t3, t4));

		customerList.add(customer1);
		customerList.add(customer2);
	}

	/**
	 * Test method for verifying the retrieval of all customers with their rewards.
	 * This test checks that the API correctly returns a list of all customers
	 * along with their associated rewards points.
	 */
	@Test
	public void testGetAllCustomersWithRewards() {
		when(customerRepository.findAll()).thenReturn(customerList);

		Map<String, Object> rewardsCustomer1 = new HashMap<>();
		rewardsCustomer1.put("totalPoints", 320);
		rewardsCustomer1.put("monthlyPoints", Map.of("July", 320));

		Map<String, Object> rewardsCustomer2 = new HashMap<>();
		rewardsCustomer2.put("totalPoints", 225);
		rewardsCustomer2.put("monthlyPoints", Map.of("August", 75, "September", 150));

		when(calculatingRewardsPoints.getTotalRewardsPoints(customerList.get(0).getTransactionsList()))
				.thenReturn(rewardsCustomer1);
		when(calculatingRewardsPoints.getTotalRewardsPoints(customerList.get(1).getTransactionsList()))
				.thenReturn(rewardsCustomer2);

		List<Map<String, Object>> result = customerService.getAllCustomersWithRewards();

		Map<String, Object> result1 = result.get(0);
		assertEquals(1L, result1.get("customerId"));
		assertEquals("Sachin Pawar", result1.get("customerName"));
		assertEquals(rewardsCustomer1, result1.get("rewardsPoints"));

		Map<String, Object> result2 = result.get(1);
		assertEquals(2L, result2.get("customerId"));
		assertEquals("Bob Smith", result2.get("customerName"));
		assertEquals(rewardsCustomer2, result2.get("rewardsPoints"));
	}
}

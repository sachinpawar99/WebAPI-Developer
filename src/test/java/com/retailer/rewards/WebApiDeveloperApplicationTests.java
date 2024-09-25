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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class WebApiDeveloperApplicationTests {
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private CalculatingRewardsPoints calculatingRewardsPoints;
	@InjectMocks
	private CustomerServiceImpl customerService;
	private List<Customer> customerList;
	private Pageable pageable;

	@BeforeEach
	public void setUp() {

		MockitoAnnotations.openMocks(this);

		// Sample customer list data
		customerList = new ArrayList<>();

		// Create customers
		Customer customer1 = new Customer();
		customer1.setCustomerId(1L);
		customer1.setCustomerName("Sachin Pawar");

		Transactions t1 = new Transactions(1L, 120.5, LocalDate.of(2024, 7, 15), customer1);
		Transactions t2 = new Transactions(2L, 200.0, LocalDate.of(2024, 7, 18), customer1);
		customer1.setTransactionsList(Arrays.asList(t1, t2));
//===================================================
		Customer customer2 = new Customer();
		customer2.setCustomerId(2L);
		customer2.setCustomerName("Bob Smith");

		Transactions t3 = new Transactions(3L, 75.0, LocalDate.of(2024, 8, 5), customer2);
		Transactions t4 = new Transactions(4L, 150.75, LocalDate.of(2024, 9, 10), customer2);
		customer2.setTransactionsList(Arrays.asList(t3, t4));

		customerList.add(customer1);
		customerList.add(customer2);


		pageable = PageRequest.of(0, 10);
	}

	@Test
	public void testGetAllCustomersWithRewards() {


		Page<Customer> customerPage = new PageImpl<>(customerList, pageable, customerList.size());
		when(customerRepository.findAll(pageable)).thenReturn(customerPage);

		// Mock rewards calculation for customer 1
		Map<String, Object> rewardsCustomer1 = new HashMap<>();
		rewardsCustomer1.put("totalPoints", 320);
		rewardsCustomer1.put("monthlyPoints", Map.of("July", 320));

		// Mock rewards calculation for customer 2
		Map<String, Object> rewardsCustomer2 = new HashMap<>();
		rewardsCustomer2.put("totalPoints", 225);
		rewardsCustomer2.put("monthlyPoints", Map.of("August", 75, "September", 150));

		when(calculatingRewardsPoints.getTotalRewardsPoints(customerList.get(0).getTransactionsList()))
				.thenReturn(rewardsCustomer1);
		when(calculatingRewardsPoints.getTotalRewardsPoints(customerList.get(1).getTransactionsList()))
				.thenReturn(rewardsCustomer2);

		Page<Map<String, Object>> resultPage = customerService.getAllCustomersWithRewards(pageable);

		assertEquals(2, resultPage.getTotalElements());

		Map<String, Object> result1 = resultPage.getContent().get(0);
		assertEquals(1L, result1.get("customerId"));
		assertEquals("Sachin Pawar", result1.get("customerName"));
		assertEquals(rewardsCustomer1, result1.get("rewardsPoints"));

		Map<String, Object> result2 = resultPage.getContent().get(1);
		assertEquals(2L, result2.get("customerId"));
		assertEquals("Bob Smith", result2.get("customerName"));
		assertEquals(rewardsCustomer2, result2.get("rewardsPoints"));
	}
}
package com.retailer.rewards.utility;

import com.retailer.rewards.controllers.CustomerController;
import com.retailer.rewards.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class CustomerPagedResponseAssembler {
    @Autowired
    private CalculatingRewardsPoints calculatingRewardsPoints;
    public Page<Map<String, Object>> toPagedModel(Page<Customer> customerPage) {
        return customerPage.map(customer -> {
            Map<String, Object> customerData = new HashMap<>();
            customerData.put("customerId", customer.getCustomerId());
            customerData.put("customerName", customer.getCustomerName());
            customerData.put("rewardsPoints", calculatingRewardsPoints.getTotalRewardsPoints(customer.getTransactionsList()));

            // Add HATEOAS links
            WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class)
                    .getRewardsPointsByCustomerId(customer.getCustomerId()));
            customerData.put("links", link.withSelfRel());

            return customerData;
        });
    }
}

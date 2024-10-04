package com.retailer.rewards.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents a customer entity in the system.
 * A customer can have multiple transactions associated with them.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    /**
     * The unique identifier for the customer.
     * This field is automatically generated and serves as the primary key.
     */
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long customerId;
    /**
     * The name of the customer.
     */
    private String customerName;

    /**
     * The list of transactions associated with the customer.
     * This is a one-to-many relationship, where each customer can have multiple transactions.
     * Cascade operations are set to all, meaning changes in the customer will cascade to transactions.
     * Lazy fetching is used to load the transactions when needed.
     *
     * {@link JsonManagedReference} is used to handle forward references in bidirectional relationships during serialization.
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Transactions> transactionsList;
}

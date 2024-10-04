package com.retailer.rewards.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Represents a Transactions entity in the system.
 * Each transaction is associated with a specific customer.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transactions {
    /**
     * The unique identifier for the transaction.
     * This field is automatically generated and serves as the primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;
    /**
     * The amount of the transaction.
     */
    private double transactionsAmount;
    /**
     * The date when the transaction was made.
     * This field is automatically set to the current date when the transaction is persisted.
     */
    private LocalDate transactionDate;
    /**
     * The customer associated with the transaction.
     * This is a many-to-one relationship, where multiple transactions can belong to a single customer.
     * Lazy fetching is used to load the customer details when needed.
     *
     * {@link JsonBackReference} is used to handle the backward reference in a bidirectional relationship during serialization.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private Customer customer;

    /**
     * Sets the transaction date to the current date before the transaction is persisted.
     * This method is automatically called before the entity is saved in the database.
     */
    @PrePersist
    public void perPersist(){
        this.transactionDate = LocalDate.now();
    }
}

package com.retailer.rewards.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for carrying transaction-related information.
 * This object is used to transfer data between the client and server, specifically
 * for transactions.
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TransactionDto {
    /**
     * The ID of the customer associated with the transaction.
     */
    private long customerId;
    /**
     * The amount of the transaction.
     */
    private double transactionAmount;
}

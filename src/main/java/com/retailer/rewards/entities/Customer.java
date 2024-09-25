package com.retailer.rewards.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long customerId;
    private String customerName;

    //@Transient
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // This handles the forward reference for serialization
    private List<Transactions> transactionsList;
}

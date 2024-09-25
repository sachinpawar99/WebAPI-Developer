# Retailer Rewards Program

## Overview

This project implements a rewards program for a retailer's customers, allowing the calculation of reward points based on their transactions. The application is built using Spring Boot and adheres to RESTful principles, providing an API for clients to access customer reward information.

## Requirements

Customers earn points based on:
- **2 points** for every dollar spent **over $100**.
- **1 point** for every dollar spent **between $50 and $100**.

**Example:**  
A purchase of **$120** yields:
\[ 2 \times (120 - 100) + 1 \times (100 - 50) = 90 \text{ points} \]

## Features

- RESTful API to retrieve customer reward points.
- Ability to add customers and their transactions.
- Example dataset included.

## Technologies Used

- Java 17 and above
- Spring Boot -> 3+ version
- Maven
- Database -> H2 or Mysql

## Getting Started

### Prerequisites

- JDK 17 or higher
- Maven

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/sachinpawar99/WebAPI-Developer.git
   cd rewards-program

### ENDPoints
1. Get RewardsPoints by CustomerId ->
                   GET - http://localhost:8080/api/customer/rewards/<customer_Id>
2. Get Rewards Points of All Customers ->
        GET - http://localhost:8080/api/customer/rewards/?page=0&size=10&sort=customerName,asc
   or   GET - http://localhost:8080/api/customer/rewards/<customer_Id>

3. Add Customer Details ->
      Request -> {"customerName":"Rahul kumar"}
      POST - http://localhost:8080/api/customer/rewards/
5. Add Transaction Details ->
       Request -> {
             "customerId":10,
             "transactionAmount":225  }
    POST - http://localhost:8080/api/transaction/

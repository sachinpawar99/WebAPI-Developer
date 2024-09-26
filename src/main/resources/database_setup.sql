-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS rewardsdb;
USE rewardsdb;

-- Create Customer Table
CREATE TABLE IF NOT EXISTS Customer (
    customer_Id INT PRIMARY KEY,
    customer_Name VARCHAR(255) NOT NULL
);

-- Create Transactions Table
CREATE TABLE IF NOT EXISTS Transactions (
    transaction_Id INT PRIMARY KEY,
    transactions_Amount DECIMAL(10, 2) NOT NULL,
    transaction_Date DATE NOT NULL,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_Id) ON DELETE CASCADE
);

-- Insert Sample Data into Customer Table
INSERT INTO Customer (customer_Id, customer_Name) VALUES
(1, 'Sachin Pawar'),
(2, 'Bob Smith'),
(3, 'Charlie Brown'),
(4, 'Diana Prince'),
(5, 'Ethan Hunt');

-- Insert Sample Data into Transactions Table
INSERT INTO Transactions (transaction_Id, transactions_Amount, transaction_Date, customer_id) VALUES
(1, 175.00, '2024-06-15', 1),
(2, 200.00, '2024-07-18', 1),
(3, 75.00, '2024-08-05', 1),
(4, 150.75, '2024-08-05', 1),
(5, 95.00, '2024-08-25', 1),
(6, 300.00, '2024-09-10', 1),
(7, 50.00, '2024-07-20', 2),
(8, 60.00, '2024-08-01', 2),
(9, 150.00, '2024-08-05', 2),
(10, 85.50, '2024-09-10', 2),
(11, 110.00, '2024-09-15', 2),
(12, 40.00, '2024-07-01', 3),
(13, 100.00, '2024-08-05', 3),
(14, 45.75, '2024-08-05', 3),
(15, 60.50, '2024-09-10', 3),
(16, 80.00, '2024-09-20', 3),
(17, 130.00, '2024-07-10', 4),
(18, 150.00, '2024-08-01', 4),
(19, 200.00, '2024-08-01', 4),
(20, 70.50, '2024-09-05', 4),
(21, 90.00, '2024-09-15', 4),
(22, 60.00, '2024-07-15', 5),
(23, 75.00, '2024-08-05', 5),
(24, 125.00, '2024-08-05', 5),
(25, 140.00, '2024-09-10', 5),
(26, 80.00, '2024-09-15', 5);

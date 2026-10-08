DROP DATABASE IF EXISTS BankApp;

CREATE DATABASE BankApp;

USE BankApp;

CREATE TABLE bankAccount(
                            accountNumber INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            balance DOUBLE NOT NULL
);



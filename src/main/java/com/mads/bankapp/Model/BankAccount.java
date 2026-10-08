package com.mads.bankapp.Model;

public class BankAccount {
    private int accountNumber;
    private String name;
    private double balance;

    public BankAccount() {
        this.balance = 0;
    }

    public BankAccount(int accountNumber, String name) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = 0;
    }

    public BankAccount(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

//    public void deposit(double amount) {
//        if(amount > 0) {
//            this.balance += amount;
//        }
//    }
//
//    public double withdraw(double amount){
//        if(amount > 0 && amount <= this.balance) {
//            this.balance -= amount;
//            return amount;
//        }else {
//            return 0;
//        }
//    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format("""
                Account Number: %d
                Name: %s
                Balance: %.2f kr.
                """, this.accountNumber, this.name, this.balance);
    }
}

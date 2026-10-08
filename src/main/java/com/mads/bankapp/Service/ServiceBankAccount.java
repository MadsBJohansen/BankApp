package com.mads.bankapp.Service;

import com.mads.bankapp.Exceptions.BankAccountException;
import com.mads.bankapp.Model.BankAccount;
import com.mads.bankapp.Repository.RepoBankAccount;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceBankAccount {
    private final RepoBankAccount repoBankAccount;

    public ServiceBankAccount(RepoBankAccount repoBankAccount) {
        this.repoBankAccount = repoBankAccount;
    }

    public List<BankAccount> getAllBankAccounts(){
        return repoBankAccount.findAll();
    }

    public BankAccount getBankAccount(int accountNumber){
        validateAccountNumber(accountNumber);
        try{
            return repoBankAccount.findById(accountNumber);
        }catch (EmptyResultDataAccessException e) {
            throw new BankAccountException("Account not found");
        }
    }

    @Transactional
    public void withdraw(int accountNumber,
                         double amount){
        validateAmount(amount);
        BankAccount bankAccount = getBankAccount(accountNumber);
        double newBalance = bankAccount.getBalance() - amount;
        repoBankAccount.updateBalance(accountNumber, newBalance);
    }

    @Transactional
    public void deposit(int accountNumber,
                        double amount) {
        validateAmount(amount);
        BankAccount bankAccount = getBankAccount(accountNumber);
        double newBalance = bankAccount.getBalance() + amount;
        repoBankAccount.updateBalance(accountNumber, newBalance);
    }

    //Mangler at tilføje exceptions og lock
    @Transactional
    public void transfer(int fromAccountNumber,
                         int toAccountNumber,
                         double amount) {
        validateAmount(amount);
        BankAccount from = getBankAccount(fromAccountNumber);
        BankAccount to = getBankAccount(toAccountNumber);
        double fromNewBalance = from.getBalance() - amount;
        double toNewBalance = to.getBalance() + amount;
        repoBankAccount.updateBalance(from.getAccountNumber(), fromNewBalance);
        repoBankAccount.updateBalance(to.getAccountNumber(), toNewBalance);
    }

    public void createAccount(BankAccount bankAccount){
        repoBankAccount.insertInto(bankAccount);
    }

    public void deleteAccount(int accountNumber){
        repoBankAccount.deleteById(accountNumber);
    }

    public void validateAmount(double amount) {
        if(amount <= 0){
            throw new BankAccountException("Amount must be more than 0");
        }
    }

    public void validateAccountNumber(int accountNumber) {
        if(accountNumber <= 0){
            throw new BankAccountException("Invalid account number");
        }
    }
}
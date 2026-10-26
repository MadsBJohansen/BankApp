package com.mads.bankapp;

import com.mads.bankapp.Exceptions.BankAccountException;
import com.mads.bankapp.Model.BankAccount;
import com.mads.bankapp.Repository.RepoBankAccount;
import com.mads.bankapp.Service.ServiceBankAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceBankAccountTests {

    @Mock
    private RepoBankAccount repoBankAccount;

    @InjectMocks
    private ServiceBankAccount serviceBankAccount;


    @Test
    void transferHappyFlow(){
        // 1. Assumptions
        BankAccount from = new BankAccount(1, "Mads", 1000);
        BankAccount to = new BankAccount(2, "Nadia", 1000);

        given(repoBankAccount.findById(1)).willReturn(from);
        given(repoBankAccount.findById(2)).willReturn(to);

        // 2. Call
        serviceBankAccount.transfer(1,2,100);

        // 3. Verify
        verify(repoBankAccount).updateBalance(1, 900);
        verify(repoBankAccount).updateBalance(2, 1100);

    }

    @Test
    void transferReceiverNotFound(){
        // 1. Assumptions
        BankAccount from = new BankAccount(1, "Mads", 1000);
        given(repoBankAccount.findById(1)).willReturn(from);
        given(repoBankAccount.findById(2)).willThrow(new EmptyResultDataAccessException(1));

        // 2 + 3. Call & Validation
        assertThrows(BankAccountException.class, () -> serviceBankAccount.transfer(1,2,100));

        verify(repoBankAccount, never()).updateBalance(anyInt(), anyDouble());
    }

    @Test
    void transferInvalidAmount(){
        assertThrows(BankAccountException.class, () -> serviceBankAccount.transfer(1,2,0));
        verifyNoInteractions(repoBankAccount);
    }

    @Test
    void transferInsufficientFunds(){

        // 1. Assumptions
        BankAccount from = new BankAccount(1, "Mads", 1000);
        BankAccount to = new BankAccount(2, "Nadia", 1000);

        given(repoBankAccount.findById(1)).willReturn(from);
        given(repoBankAccount.findById(2)).willReturn(to);

        // 2. Call
        assertThrows(BankAccountException.class, () -> serviceBankAccount.transfer(1, 2, 2000));

        // 3. Validation
        verify(repoBankAccount, never()).updateBalance(anyInt(), anyDouble());
    }

    @Test
    void transferNegativAmount(){
        assertThrows(BankAccountException.class, () -> serviceBankAccount.transfer(1, 2, -100));
        verifyNoInteractions(repoBankAccount);
    }

    @Test
    void depositHappyFlow(){
        // 1. Assumptions
        BankAccount bankAccount = new BankAccount(1, "Mads", 1000);
        given(repoBankAccount.findById(1)).willReturn(bankAccount);

        // 2. Call
        serviceBankAccount.deposit(1,200);

        // 3. Validation
        verify(repoBankAccount).updateBalance(1, 1200);
    }

    @Test
    void withdrawInsufficientFunds(){

        // 1. Assumptions
        BankAccount bankAccount = new BankAccount(1, "Mads", 1000);
        given(repoBankAccount.findById(1)).willReturn(bankAccount);

        // 2. Call
        assertThrows(BankAccountException.class, () -> serviceBankAccount.withdraw(1,2000));

        // 3. Validation
        verify(repoBankAccount, never()).updateBalance(anyInt(),anyDouble());
    }

    @Test
    void withdrawExactBalance(){

        // 1. Assumptions
        BankAccount bankAccount = new BankAccount(1, "Mads", 1000);
        given(repoBankAccount.findById(1)).willReturn(bankAccount);

        // 2. Call
        serviceBankAccount.withdraw(1,1000);

        // 3. Validation
        verify(repoBankAccount).updateBalance(1,0);
    }

    @Test
    void withdrawHappyFlow(){

        // 1. Assumptions
        BankAccount bankAccount = new BankAccount(1, "Mads", 1000);
        given(repoBankAccount.findById(1)).willReturn(bankAccount);

        // 2. Call
        serviceBankAccount.withdraw(1,1000);

        // 3. Validation
        verify(repoBankAccount).updateBalance(1,0);
    }
}

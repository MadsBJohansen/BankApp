package com.mads.bankapp;

import com.mads.bankapp.Model.BankAccount;
import com.mads.bankapp.Repository.RepoBankAccount;
import com.mads.bankapp.Service.ServiceBankAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest

class ServiceBankAccountSpringIntegrationsTests {

    @MockitoBean
    private RepoBankAccount repoBankAccount;

    @Autowired
    private ServiceBankAccount serviceBankAccount;

    @Test
    void transferTest(){
        when(repoBankAccount.findById(1)).thenReturn(new BankAccount(1,"Mads",1000));
        when(repoBankAccount.findById(2)).thenReturn(new BankAccount(2,"Nadia",1000));

        serviceBankAccount.transfer(1,2,100);

        verify(repoBankAccount).updateBalance(1, 900);
        verify(repoBankAccount).updateBalance(2, 1100);

    }
}

package com.mads.bankapp;

import com.mads.bankapp.Controller.ControllerBankAccountCLAUDE;
import com.mads.bankapp.Model.BankAccount;
import com.mads.bankapp.Service.ServiceBankAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ControllerBankAccountCLAUDETest {

    @Mock
    private Model model;

    @Mock
    private ServiceBankAccount serviceBankAccount;

    @InjectMocks
    private ControllerBankAccountCLAUDE controllerBankAccount;

    @Test
    @DisplayName("showBankAccountHappyFlow()")
    public void showBankAccount(){

        // 1. Assumptions
        BankAccount bankAccount = new BankAccount(1, "mads", 1000);
        given(serviceBankAccount.getBankAccount(1)).willReturn(bankAccount);

        // 2. Call

        String testResult = controllerBankAccount.showBankAccount(model, 1);

        // 3. Validation
        assertEquals("bankAccountCLAUDE", testResult);
        verify(model).addAttribute("bankAccount", bankAccount);
    }
}

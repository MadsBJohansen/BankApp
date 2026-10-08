package com.mads.bankapp.Controller;

import com.mads.bankapp.Model.BankAccount;
import com.mads.bankapp.Service.ServiceBankAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControllerBankAccountCLAUDE {

    private final ServiceBankAccount serviceBankAccount;

    public ControllerBankAccountCLAUDE(ServiceBankAccount serviceBankAccount) {
        this.serviceBankAccount = serviceBankAccount;
    }

    @GetMapping("/")
    public String index() {
        return "indexCLAUDE";
    }

    @GetMapping("/bankAccounts")
    public String showAllBankAccounts(Model model) {
        model.addAttribute("bankAccounts", serviceBankAccount.getAllBankAccounts());
        return "bankAccountsCLAUDE";
    }

    @GetMapping("/bankAccounts/showCreateBankAccount")
    public String showCreateAccount(Model model) {
        model.addAttribute("bankAccount", new BankAccount());
        return "createBankAccountCLAUDE";
    }

    @GetMapping("/bankAccounts/{accountNumber}/showBankAccount")
    public String showBankAccount(Model model, @PathVariable int accountNumber) {
        model.addAttribute("bankAccount", serviceBankAccount.getBankAccount(accountNumber));
        return "bankAccountCLAUDE";
    }

    @PostMapping("/bankAccounts/{accountNumber}/withdraw")
    public String withdraw(@PathVariable int accountNumber, @RequestParam double amount) {
        serviceBankAccount.withdraw(accountNumber, amount);
        return "redirect:/bankAccounts/" + accountNumber + "/showBankAccount";
    }

    @PostMapping("/bankAccounts/{accountNumber}/transfer")
    public String transfer(@PathVariable int accountNumber,
                           @RequestParam int toAccountNumber,
                           @RequestParam double amount) {
        serviceBankAccount.transfer(accountNumber, toAccountNumber, amount);
        return "redirect:/bankAccounts/" + accountNumber + "/showBankAccount";
    }

    @PostMapping("/bankAccounts/{accountNumber}/deposit")
    public String deposit(@PathVariable int accountNumber, @RequestParam double amount) {
        serviceBankAccount.deposit(accountNumber, amount);
        return "redirect:/bankAccounts/" + accountNumber + "/showBankAccount";
    }

    @PostMapping("/bankAccounts/createBankAccount")
    public String createAccount(@ModelAttribute BankAccount bankAccount) {
        serviceBankAccount.createAccount(bankAccount);
        return "redirect:/bankAccounts";
    }

    @PostMapping("/bankAccounts/{accountNumber}/deleteAccount")
    public String deleteAccount(@PathVariable int accountNumber) {
        serviceBankAccount.deleteAccount(accountNumber);
        return "redirect:/bankAccounts";
    }
}

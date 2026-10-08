//package com.mads.bankapp.Controller;
//
//import com.mads.bankapp.Model.BankAccount;
//import com.mads.bankapp.Service.ServiceBankAccount;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//
//@Controller
//@RequestMapping("/bankAccounts")
//public class ControllerBankAccount {
//
//    private final ServiceBankAccount serviceBankAccount;
//
//    public ControllerBankAccount(ServiceBankAccount serviceBankAccount) {
//        this.serviceBankAccount = serviceBankAccount;
//    }
//
//    @GetMapping
//    public String showAllBankAccounts(Model model){
//        model.addAttribute("bankAccounts",
//                serviceBankAccount.getAllBankAccounts());
//        return "bankAccounts";
//    }
//
//    @GetMapping("/showCreateBankAccount")
//    public String showCreateAccount(Model model){
//        model.addAttribute("bankAccount", new BankAccount());
//        return "createBankAccount";
//    }
//
//    @GetMapping("/{accountNumber}/showBankAccount")
//    public String showBankAccount(Model model,
//                                  @PathVariable int accountNumber){
//        model.addAttribute("bankAccount",
//                serviceBankAccount.getBankAccount(accountNumber));
//        return "bankAccount";
//    }
//
//    @PostMapping("/{accountNumber}/withdraw")
//    public String withdraw(@PathVariable int accountNumber,
//                           @RequestParam double amount){
//        serviceBankAccount.withdraw(accountNumber, amount);
//        return "redirect:/bankAccount";
//    }
//
//    @PostMapping("/{accountNumber}/transfer")
//    public String transfer(@PathVariable int accountNumber,
//                           @RequestParam int toAccountNumber,
//                           @RequestParam double amount){
//        serviceBankAccount.transfer(accountNumber,toAccountNumber, amount);
//        return "redirect:/bankAccount";
//    }
//
//    @PostMapping("/{accountNumber}/deposit")
//    public String deposit(@PathVariable int accountNumber,
//                          @RequestParam double amount){
//        serviceBankAccount.deposit(accountNumber, amount);
//        return "redirect:/bankAccount";
//    }
//
//    @PostMapping("/createBankAccount")
//    public String createAccount(@ModelAttribute BankAccount bankAccount){
//        serviceBankAccount.createAccount(bankAccount);
//        return "redirect:/bankAccounts";
//    }
//
//    @PostMapping("/{accountNumber}/deleteAccount")
//    public String deleteAccount(@PathVariable int accountNumber){
//        serviceBankAccount.deleteAccount(accountNumber);
//        return "redirect:/bankAccounts";
//    }
//}

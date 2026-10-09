package com.mads.bankapp.Controller;

import com.mads.bankapp.Exceptions.BankAccountException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BankAccountException.class)
        public String handleBankAccountException(BankAccountException e, Model model, HttpServletRequest request){
            String[] requestAsList = request.getRequestURI().split("/");
            int accountNumber = Integer.parseInt(requestAsList[2]);
            model.addAttribute("accountNumber", accountNumber);
            model.addAttribute("errorMessage",e.getMessage() );
            return "error";
        }

}
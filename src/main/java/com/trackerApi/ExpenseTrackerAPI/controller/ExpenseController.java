package com.trackerApi.ExpenseTrackerAPI.controller;

import com.trackerApi.ExpenseTrackerAPI.module.Expense;
import com.trackerApi.ExpenseTrackerAPI.service.AuthenticationService;
import com.trackerApi.ExpenseTrackerAPI.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;

    @Autowired
    AuthenticationService authenticationService;


    //createExpenses
    @PostMapping()
    public String createExpenses(@RequestParam String userEmail , @RequestParam String token , @RequestBody Expense expense){
        if(authenticationService.authenticate(userEmail , token)){
            expenseService.addExpenses(expense , token);
            return "New expenses has been added...!!!";
        }else {
            return "Sorry the userEmail has not been found please check emailAddress or try to signUp";
        }
    }

    //readAllExpenses
    @GetMapping()
    public Object readAllExpenses(@RequestParam String userEmail, @RequestParam String token) {
        if (authenticationService.authenticate(userEmail, token)) {
            return expenseService.getAllExpenses();
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Sorry, the userEmail has not been found. Please check the emailAddress or try to signUp.");
            return errorResponse;
        }
    }

    //update Expenses
    @PutMapping("/{id}")
    public Object updateExpenses(@PathVariable Long id, @RequestBody Expense updatedExpense, @RequestParam String userEmail, @RequestParam String token) {
        if (authenticationService.authenticate(userEmail, token)) {
            Expense expense = expenseService.getExpenseById(id);

            if (expense != null) {
                expense.setTitle(updatedExpense.getTitle());
                expense.setDescription(updatedExpense.getDescription());
                expense.setPrice(updatedExpense.getPrice());

                expenseService.updateExpense(expense);

                return expense;
            } else {
                throw new IllegalStateException("Expense not found with ID: " + id);
            }
        } else {
            throw new IllegalStateException("Sorry, the userEmail has not been found. Please check the emailAddress or try to signUp.");
        }
    }

//deleteExpense
    @DeleteMapping("/{id}")
    public Object deleteExpenseById(@PathVariable Long id, @RequestParam String userEmail, @RequestParam String token) {
        if (authenticationService.authenticate(userEmail, token)) {
            Expense expense = expenseService.getExpenseById(id);

            if (expense != null) {
                expenseService.deleteExpenseUsingId(expense);
                return "Expenses data Erased";
            } else {
                throw new IllegalStateException("The Id which your are using is not found ");
            }
        } else {
            throw new IllegalStateException("Sorry, the userEmail has not been found. Please check the emailAddress or try to signUp.");
        }
    }


//  API to fetch all the product for a particular date and time
    @GetMapping("/date-time")
    public List<Expense> fetchAllExpenseForAParticularDateAndTIme(@RequestParam(required = false) String date, @RequestParam(required = false) String time, @RequestParam String userEmail, @RequestParam String token) {
        if (authenticationService.authenticate(userEmail, token)) {
            if (date != null && time != null) {
                LocalDate localDate = LocalDate.parse(date);
                LocalTime localTime = LocalTime.parse(time);
                return expenseService.fetchAllExpenseForAParticularByTImeData(localDate, localTime);
            } else if (date != null) {
                LocalDate localDate = LocalDate.parse(date);
                return expenseService.fetchAllExpenseForAParticularByDateData(localDate);
            } else {
                return expenseService.getAllExpenses();
            }
        } else {
            throw new IllegalStateException("Sorry, the userEmail has not been found. Please check the emailAddress or try to signUp.");
        }
    }


}

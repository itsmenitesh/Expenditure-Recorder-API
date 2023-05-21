package com.trackerApi.ExpenseTrackerAPI.service;

import com.trackerApi.ExpenseTrackerAPI.module.AuthenticationToken;
import com.trackerApi.ExpenseTrackerAPI.module.Expense;
import com.trackerApi.ExpenseTrackerAPI.repository.IAuthenticationRepository;
import com.trackerApi.ExpenseTrackerAPI.repository.IExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    IExpenseRepository iExpenseRepository;

    @Autowired
    IAuthenticationRepository iAuthenticationRepository;

    //Create Expenses
    public void addExpenses(Expense expense, String token) {
        AuthenticationToken authToken = iAuthenticationRepository.findFirstByToken(token);
        if (authToken != null) {
            iExpenseRepository.save(expense);
        }

    }

    //ReadAllExpenses
    public List<Expense> getAllExpenses() {
        return iExpenseRepository.findAll();
    }


//updateExpensesById
public void updateExpense(Expense updatedExpense) {
    Expense expense = iExpenseRepository.findById(updatedExpense.getId()).orElse(null);
    if (expense != null) {
        expense.setTitle(updatedExpense.getTitle());
        expense.setDescription(updatedExpense.getDescription());
        expense.setPrice(updatedExpense.getPrice());

        iExpenseRepository.save(expense);
    } else {
        throw new IllegalStateException("Id is not valid. Please check the expense ID once.");
    }
}



//updateExpensesById
    public Expense getExpenseById(Long id) {
        return iExpenseRepository.findById(id).orElse(null);

    }



    //deleteExpensesData
    public void deleteExpenseUsingId(Expense expense) {
        iExpenseRepository.deleteById(expense.getId());
    }


    public List<Expense> fetchAllExpenseForAParticularByTImeData(LocalDate date, LocalTime time) {
        return iExpenseRepository.findByDateAndTime(date, time);
    }

    public List<Expense> fetchAllExpenseForAParticularByDateData(LocalDate date) {
        return iExpenseRepository.findByDate(date);
    }



}

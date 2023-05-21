package com.trackerApi.ExpenseTrackerAPI.repository;

import com.trackerApi.ExpenseTrackerAPI.module.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface IExpenseRepository extends JpaRepository<Expense , Long> {

    List<Expense> findByDateAndTime(LocalDate date, LocalTime time);

    List<Expense> findByDate(LocalDate date);

}

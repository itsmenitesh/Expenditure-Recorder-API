package com.trackerApi.ExpenseTrackerAPI.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "title value is mandatory please provide proper title")
    private String title;

    private String description;

    @Positive(message = "enter the price of product")
    private double price;

    @NotNull(message = "Date is required")
    private LocalDate date;

    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}

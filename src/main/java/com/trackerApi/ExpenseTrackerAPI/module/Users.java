package com.trackerApi.ExpenseTrackerAPI.module;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private String userPhoneNumber;
    private Integer userAge;

    public Users(String userFirstName, String userLastName, String userEmail, String userPassword, String userPhoneNumber, Integer userAge) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
        this.userAge = userAge;
    }
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Expense> expenses;

}

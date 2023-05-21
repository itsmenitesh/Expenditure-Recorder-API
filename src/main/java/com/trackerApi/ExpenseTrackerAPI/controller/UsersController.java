package com.trackerApi.ExpenseTrackerAPI.controller;

import com.trackerApi.ExpenseTrackerAPI.dto.SignInInput;
import com.trackerApi.ExpenseTrackerAPI.dto.SignInOutput;
import com.trackerApi.ExpenseTrackerAPI.dto.SignUpInput;
import com.trackerApi.ExpenseTrackerAPI.dto.SignUpOutput;
import com.trackerApi.ExpenseTrackerAPI.service.AuthenticationService;
import com.trackerApi.ExpenseTrackerAPI.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UsersController {
    @Autowired
    UsersService usersService;

    @Autowired
    AuthenticationService authenticationService;

    //sign up
    @PostMapping("/signup")
    public SignUpOutput signUp(@RequestBody SignUpInput signUpDto){
        return usersService.singUp(signUpDto);
    }


    //SingIn
    @PostMapping("/signing")
    public SignInOutput signInUser(@RequestBody SignInInput signInInDto){
        return  usersService.signIn(signInInDto);
    }
}

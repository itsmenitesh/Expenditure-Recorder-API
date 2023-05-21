package com.trackerApi.ExpenseTrackerAPI.service;

import com.trackerApi.ExpenseTrackerAPI.dto.SignInInput;
import com.trackerApi.ExpenseTrackerAPI.dto.SignInOutput;
import com.trackerApi.ExpenseTrackerAPI.dto.SignUpInput;
import com.trackerApi.ExpenseTrackerAPI.dto.SignUpOutput;
import com.trackerApi.ExpenseTrackerAPI.module.AuthenticationToken;
import com.trackerApi.ExpenseTrackerAPI.module.Users;
import com.trackerApi.ExpenseTrackerAPI.repository.IAuthenticationRepository;
import com.trackerApi.ExpenseTrackerAPI.repository.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UsersService {
    @Autowired
    IUsersRepository iUsersRepository;

    @Autowired
    IAuthenticationRepository iAuthenticationRepository;

    @Autowired
    AuthenticationService authenticationService;

    public SignUpOutput singUp(SignUpInput signUpDto) {

        //1st. check if user already exists or not using user Email
        Users users = iUsersRepository.findFirstByUserEmail(signUpDto.getUserEmail());
        if(users != null) {
            throw new IllegalStateException("This UserEmail already Exists !!! .. Please try to Login");
        }

        //2nd. password encryption
        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signUpDto.getUserPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        //3rd. save the user data

        users = new Users(signUpDto.getUserFirstName() ,signUpDto.getUserLastName() ,signUpDto.getUserEmail() , encryptedPassword , signUpDto.getUserPhoneNumber() ,signUpDto.getUserAge());
        iUsersRepository.save(users);

        //4th. Token create
        AuthenticationToken token = new AuthenticationToken(users);
        authenticationService.saveToken(token);

        //5th. return singUpOutput
        return new SignUpOutput("User registered" , "Account Created successfully");
    }

    //encryption method
    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);
        return hash;
    }


    //signIn
    public SignInOutput signIn(SignInInput signInInDto) {
        //1st. check email exists
        Users users = iUsersRepository.findFirstByUserEmail(signInInDto.getUserEmail());
        if(users == null) {
            throw new IllegalStateException("This User is not Exists !!! .. Please try to Create account or please signUp");
        }

        //2nd. encrypt the password
        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signInInDto.getUserPassword());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


        //3rd. match it with database encrypted password
        boolean isPasswordValid = encryptedPassword.equals(users.getUserPassword());
        if(!isPasswordValid){
            throw new IllegalStateException("Incorrect Password !!! .. Please re-check");
        }

        //4th. figure out the token
//        AuthenticationToken authToken = authenticationService.getToken(users);
        AuthenticationToken authToken = authenticationService.getToken(users);

        //5th. set up output response
        return new SignInOutput("Authentication successfully",authToken.getToken());

    }




}

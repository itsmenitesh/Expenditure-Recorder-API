package com.trackerApi.ExpenseTrackerAPI.service;

import com.trackerApi.ExpenseTrackerAPI.module.AuthenticationToken;
import com.trackerApi.ExpenseTrackerAPI.module.Users;
import com.trackerApi.ExpenseTrackerAPI.repository.IAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    IAuthenticationRepository iAuthenticationRepository;

    public void saveToken(AuthenticationToken token) {
        iAuthenticationRepository.save(token);
    }

    public AuthenticationToken getToken(Users users) {
      return   iAuthenticationRepository.findByUsers(users);
    }

    // for authenticate a valid user
    public boolean authenticate(String userEmail, String token) {
        if(token == null && userEmail == null){
            return false;
        }
        AuthenticationToken authToken = iAuthenticationRepository.findFirstByToken(token);
        if(authToken == null){
            return false;
        }
        String validEmail = authToken.getUsers().getUserEmail();
        return validEmail.equals(userEmail);
    }
}

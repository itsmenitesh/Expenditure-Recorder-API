package com.trackerApi.ExpenseTrackerAPI.repository;

import com.trackerApi.ExpenseTrackerAPI.module.AuthenticationToken;
import com.trackerApi.ExpenseTrackerAPI.module.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepository extends JpaRepository<AuthenticationToken ,Long> {

    AuthenticationToken findByUsers(Users users);

    AuthenticationToken findFirstByToken(String token);
}

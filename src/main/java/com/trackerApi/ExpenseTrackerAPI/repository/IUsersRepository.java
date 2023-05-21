package com.trackerApi.ExpenseTrackerAPI.repository;

import com.trackerApi.ExpenseTrackerAPI.module.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsersRepository extends JpaRepository<Users , Long> {
    Users findFirstByUserEmail(String userEmail);
}

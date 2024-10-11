package com.example.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Account;

import java.util.Optional;
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
    Optional<Account> findByUsername(String name);

    @Query("FROM Account WHERE username = :usernameVar AND password = :passwordVar")
    Account verAccount(@Param("usernameVar") String username, @Param("passwordVar") String password);
}

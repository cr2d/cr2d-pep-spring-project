package com.example.service;

import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class AccountService {
    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account addUser(Account account){
        if(account.getUsername() != "" && account.getPassword().length() > 3){
            return accountRepository.save(account);
        }
        return null;
    }
}

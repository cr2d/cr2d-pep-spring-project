package com.example.service;

import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
//import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
@Service
public class AccountService {
    AccountRepository accountRepository;
    //MessageRepository messageRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
       // this.messageRepository = messageRepository;
    }

    public Account addUser(Account account){
        Optional<Account> accountUser = accountRepository.findByUsername(account.getUsername());
        if(account.getUsername() != "" && account.getPassword().length() > 3){
            if(accountUser.isEmpty()){
                return accountRepository.save(account);
            }
        }
        return null;
    }
    public Account checkUsers(Account account){
        Account check = accountRepository.verAccount(account.getUsername(), account.getPassword());
       // if(check.getUsername() == null){
         //   return null;
        //}
        return check;
    }
}

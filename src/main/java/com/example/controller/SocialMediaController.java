package com.example.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.*;
import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getMessagesByUser(@PathVariable Integer accountId){
        List<Message> messages = messageService.findAllMessagesByUser(accountId);
        return ResponseEntity.ok(messages);
    }
    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account newUser) {
        // Logic to register a new user
        Account add = accountService.addUser(newUser);
        if(add == null){
            return ResponseEntity.status(409).body(null);
        }
        return ResponseEntity.ok(add);
    }
    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account loginRequest) {
        // Logic to authenticate user login
        Account verify = accountService.checkUsers(loginRequest);
        if(verify == null){
            return ResponseEntity.status(401).body(null);
        }
        return ResponseEntity.ok(verify);
    }
    @PostMapping("/messages")
    public ResponseEntity<Message> newPost(@RequestBody Message message){
        Message send = messageService.postMessage(message);
        if(send == null){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.ok(send);

    }
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAll(){
        List<Message> messages = messageService.findAllMessages();
        return ResponseEntity.ok(messages);
    }
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId ){
        Message message = messageService.getMessageById(messageId);
        if(message == null){
            return ResponseEntity.status(200).body(null);
        }
        return ResponseEntity.ok(message);
    }
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMesssageById(@PathVariable Integer messageId ){
        Message message = messageService.deleteMesssage(messageId);
        if(message == null){
            return ResponseEntity.status(200).body(null);
        }
        return ResponseEntity.ok(1);
    }
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessage(@PathVariable Integer messageId, @RequestBody Message message){
        Message newMessage = messageService.update(message,messageId);
        if(newMessage == null){
            return ResponseEntity.status(400).body(null);
        }
        return ResponseEntity.ok(1);
    }
}

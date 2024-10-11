package com.example.service;

import org.springframework.stereotype.Service;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
//import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.util.List;
@Service
public class MessageService {
    MessageRepository messageRepository;
   // AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
       // this.accountRepository = accountRepository;
    }
    public Message postMessage(Message message){
        Optional<Message> accountUser = messageRepository.findByPostedBy(message.getPostedBy());
        if(message.getMessageText() != "" && message.getMessageText().length() <= 255){
            if(accountUser.isPresent()){
                return messageRepository.save(message);
            }
        }
        return null;
    }
    public List<Message> findAllMessages(){
        return messageRepository.findAll();
    }
    public List<Message> findAllMessagesByUser(Integer userId){
        //return messageRepository.findAllById(userId);
        return null;
    }
    public Message getMessageById(Integer id){
        Optional<Message> message = messageRepository.findById(id);
        if(message.isPresent()){
            return message.get();
        }else{
            return null;
        }
    }
    public Message deleteMesssage(Integer id){
        Message message = getMessageById(id);
        if(message != null){
            messageRepository.deleteById(id);
            return message;
        }else{
            return null;
        }
    }
    public Message update(Message newM, Integer id){
        Message message = getMessageById(id);
        if(message != null){
            if(newM.getMessageText() != "" && newM.getMessageText().length() <= 255){
                message.setMessageText(newM.getMessageText());
                messageRepository.save(message);
                return message;
            }else{
                return null;
            }   
        }else{
            return null;
        }

    }
}

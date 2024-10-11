package com.example.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;
import java.util.Optional;
import java.util.List;
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByPostedBy(Integer name);
    //List<Message> findByPostedBy(String postedBy);
    //@Query("SELECT * FROM message WHERE postedBy = :user")
    //List<Message> findAllMByUser(@Param("user") String user);
}

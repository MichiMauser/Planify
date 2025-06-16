package com.example.planify.repository;

import com.example.planify.model.GroupChat;
import com.example.planify.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findByGroup_Id(Long group_Id);
}

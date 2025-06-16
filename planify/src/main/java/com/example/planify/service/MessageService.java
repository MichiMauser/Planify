package com.example.planify.service;

import com.example.planify.model.GroupChat;
import com.example.planify.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    public void addMessage(Message message);
    public List<Message> getMessagesByGroupChat(Long groupChatId);
}

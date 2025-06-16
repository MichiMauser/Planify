package com.example.planify.service.impl;

import com.example.planify.model.Message;
import com.example.planify.repository.MessageRepository;
import com.example.planify.service.MessageService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    public MessageImpl(MessageRepository messageRepository, SimpMessagingTemplate simpMessagingTemplate) {
        this.messageRepository = messageRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void addMessage(Message message) {
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));

        Map<String, Object> notificationPayload = new HashMap<>();
        notificationPayload.put("type", "NEW_MESSAGE");
        notificationPayload.put("sender", message.getSender());
        notificationPayload.put("groupName", message.getGroup().getName());
        notificationPayload.put("groupId", message.getGroup().getId());
        notificationPayload.put("message", message.getMessage());
        notificationPayload.put("timestamp", message.getTimestamp().getTime());
        simpMessagingTemplate.convertAndSend("/topic/notifications",notificationPayload);
        messageRepository.save(message);
    }

    @Override
    public List<Message> getMessagesByGroupChat(Long groupChatId) {
        return messageRepository.findByGroup_Id(groupChatId);
    }
}

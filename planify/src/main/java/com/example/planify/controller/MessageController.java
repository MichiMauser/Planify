package com.example.planify.controller;


import com.example.planify.model.GroupChat;
import com.example.planify.model.Message;
import com.example.planify.service.GroupChatService;
import com.example.planify.service.MessageService;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;
    private final GroupChatService groupChatService;
    public MessageController(MessageService messageService, GroupChatService groupChatService) {
        this.messageService = messageService;
        this.groupChatService = groupChatService;
    }

    @GetMapping("/{id}")
    ResponseEntity<?>  getMessageByGroupChat(@PathVariable Long id) {

        List<Message> messageList = messageService.getMessagesByGroupChat(id);
        return ResponseEntity.status(HttpStatus.OK).body(messageList);
    }

    @PostMapping("/post")
    ResponseEntity<?> postMessage(@RequestBody Message message, @RequestParam Long groupChatId) {

        Optional<GroupChat> group  = groupChatService.getGroupChatById(groupChatId);
        if(group.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group not found");
        }

        GroupChat groupChat = group.get();
        message.setGroup(groupChat);
        messageService.addMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}

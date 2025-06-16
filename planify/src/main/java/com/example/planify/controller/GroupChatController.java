package com.example.planify.controller;

import com.example.planify.model.GroupChat;
import com.example.planify.service.GroupChatService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/group")
@Controller
public class GroupChatController {

    private final GroupChatService groupChatService;

    public GroupChatController(GroupChatService groupChatService) {
        this.groupChatService = groupChatService;
    }

    @GetMapping({"","/{name}"})
    public ResponseEntity<?> getGroup(@PathVariable(required = false) String name) {
        if(name == null ||  name.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(groupChatService.getGroupChats());
        }
        return ResponseEntity.status(HttpStatus.OK).body(groupChatService.getGroupChatByName(name));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@RequestBody GroupChat groupChat) {

        GroupChat group = groupChatService.addGroup(groupChat);
        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }
}

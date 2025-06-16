package com.example.planify.service;

import com.example.planify.model.GroupChat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface GroupChatService {


    public GroupChat addGroup(GroupChat groupChat);
    public List<GroupChat> getGroupChats();
    public List<GroupChat> getGroupChatByName(String name);
    public Optional<GroupChat> getGroupChatById(Long id);
}

package com.example.planify.service.impl;

import com.example.planify.model.GroupChat;
import com.example.planify.repository.GroupChatRepository;
import com.example.planify.service.GroupChatService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GroupChatImpl implements GroupChatService {

    private final GroupChatRepository groupChatRepository;

    public GroupChatImpl(GroupChatRepository groupChatRepository) {
        this.groupChatRepository = groupChatRepository;
    }

    @Override
    public GroupChat addGroup(GroupChat groupChat) {
        return groupChatRepository.save(groupChat);
    }

    @Override
    public List<GroupChat> getGroupChats() {
        return groupChatRepository.findAll();
    }

    @Override
    public List<GroupChat> getGroupChatByName(String name) {
         return  groupChatRepository.findByName(name);
    }

    @Override
    public Optional<GroupChat> getGroupChatById(Long id) {
        return groupChatRepository.findById(id);
    }
}

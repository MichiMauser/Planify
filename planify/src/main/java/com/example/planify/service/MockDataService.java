package com.example.planify.service;

import com.example.planify.model.Notebook;
import com.example.planify.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MockDataService {
    void generateMockData();
    List<User> getUsers();
    List<Notebook> getNotebooks();
}

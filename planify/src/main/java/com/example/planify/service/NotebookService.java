package com.example.planify.service;

import com.example.planify.model.MyNotebook;
import com.example.planify.model.Notebook;
import com.example.planify.model.SmartNotebook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotebookService {
    Notebook addNotebook(Notebook notebook);
    List<Notebook> getAllNotebooks();
//    List<MyNotebook> getAllMyNotebooks();
//    List<SmartNotebook> getAllSmartNotebooks();
    Notebook findNotebookByName(String name);
    List<Notebook> removeNotebook(Notebook notebook);
    void removeNotebookByName(String name);
    List<Notebook> findNotebookByUserId(Long userId);

    Notebook updateNotebook(Notebook notebook);
}

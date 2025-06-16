package com.example.planify.service.impl;

import com.example.planify.model.MyNotebook;
import com.example.planify.model.Notebook;
import com.example.planify.model.SmartNotebook;
import com.example.planify.repository.NotebookRepository;
import com.example.planify.repository.UserRepository;
import com.example.planify.service.NotebookService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotebookImpl implements NotebookService {
   private final NotebookRepository notebookRepository;
   private final UserRepository userRepository;


    public NotebookImpl(NotebookRepository notebookRepository, UserRepository userRepository) {
        this.notebookRepository = notebookRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Notebook addNotebook(Notebook notebook) {
        notebookRepository.save(notebook);
        return notebook;
    }

    @Override
    public List<Notebook> getAllNotebooks() {
        return notebookRepository.findAll();
    }

//    @Override
//    public List<MyNotebook> getAllMyNotebooks() {
//        return notebookRepository.findMyNotebooks();
//    }

//    @Override
//    public List<SmartNotebook> getAllSmartNotebooks() {
//        return notebookRepository.findSmartNotebooks();
//    }

    @Override
    public Notebook findNotebookByName(String name) {
        Notebook notebook = notebookRepository.findByNotebookName(name);
        if(notebook != null){
            return notebook;
        }
        throw new RuntimeException("Notebook with this name does not exists");
    }

    @Override
    public List<Notebook> removeNotebook( Notebook notebook) {
        notebookRepository.delete(notebook);
        return notebookRepository.findAll();
    }

    @Override
    public void removeNotebookByName(String name) {
        notebookRepository.removeByNotebookName(name);

    }

    @Override
    public List<Notebook> findNotebookByUserId(Long userId) {
        return notebookRepository.findNotebooksByUserId(userId);
    }

    @Override
    public Notebook updateNotebook(Notebook notebook) {
        Notebook oldNotebook;
        if(notebookRepository.findById(notebook.getId()).isPresent()){
            oldNotebook = notebookRepository.findById(notebook.getId()).get();
            oldNotebook.setNotebookName(notebook.getNotebookName());
            oldNotebook.setNotes(notebook.getNotes());
            ((MyNotebook) oldNotebook).setResolution(((MyNotebook) notebook).getResolution());

            return notebookRepository.save(oldNotebook);
        }else{
            throw new RuntimeException("Notebook with this id does not exists");
        }

    }
}

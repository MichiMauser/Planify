package com.example.planify.repository;

import com.example.planify.model.MyNotebook;
import com.example.planify.model.Notebook;
import com.example.planify.model.SmartNotebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NotebookRepository extends JpaRepository<Notebook,Long> {
//    List<MyNotebook> findMyNotebooks();
    Notebook findByNotebookName(String notebookName);

//    List<SmartNotebook> findSmartNotebooks();

    List<Notebook> findNotebooksByUserId(Long userId);

//    void remove(Notebook notebook);
    void removeByNotebookName(String notebookName);

}

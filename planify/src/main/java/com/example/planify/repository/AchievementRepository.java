package com.example.planify.repository;

import com.example.planify.model.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement,Long> {
    List<Achievement> findByName(String name);

}




package com.example.planify.service;

import com.example.planify.model.Achievement;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AchievementService {
    List<Achievement> findAchievementByName(String name);
    List<Achievement> findAllAchievements();
    List<Achievement> addAchievement(Achievement achievement);
}

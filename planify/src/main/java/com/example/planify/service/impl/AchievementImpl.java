package com.example.planify.service.impl;

import com.example.planify.model.Achievement;
import com.example.planify.repository.AchievementRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AchievementImpl extends Achievement {

    private final AchievementRepository achievementRepository;

    public AchievementImpl(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    public List<Achievement> findAchievementByName(String name) {
        List<Achievement> achievement = achievementRepository.findByName(name);
        if(achievement != null){
            return achievement;
        }
        throw new RuntimeException("Achievement with this name does not exists");
    }
    public List<Achievement> findAllAchievements() {
        return achievementRepository.findAll();
    }

    public Achievement addAchievement(Achievement achievement) {
        achievementRepository.save(achievement);
        return achievement;
    }
}

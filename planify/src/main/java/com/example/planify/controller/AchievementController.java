package com.example.planify.controller;

import com.example.planify.model.Achievement;
import com.example.planify.service.AchievementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/achievements")
public class AchievementController {
    private AchievementService achievementService;

    @GetMapping()
    public ResponseEntity<?> getAchievements( @RequestParam(required = false) String name){
        if(name != null && !name.isEmpty()){
            List<Achievement> achievements = achievementService.findAchievementByName(name);
            return ResponseEntity.status(HttpStatus.OK).body(achievements);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(achievementService.findAllAchievements());
        }

    }

    @PostMapping("/add")
    public ResponseEntity<?> addAchievement(Achievement achievement){
        return ResponseEntity.status(HttpStatus.OK).body(achievementService.addAchievement(achievement));
    }

}

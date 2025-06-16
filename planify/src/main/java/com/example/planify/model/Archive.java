package com.example.planify.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Archive extends Notebook {
    private String title;
    private Date date;
    private String analyzeResponse;
    @OneToMany

    private List<Notebook> notebook;



}

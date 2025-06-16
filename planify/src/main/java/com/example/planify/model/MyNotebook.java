package com.example.planify.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.DayOfWeek;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class MyNotebook extends Notebook {
    @NotBlank
    private String resolution;
    private Date date;





//    private DayOfWeek dayOfWeek;
}

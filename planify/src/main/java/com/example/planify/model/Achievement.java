package com.example.planify.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Achievement {
    private Integer number;
    private String name;
    private Date date;
    @ManyToMany
    private List<User> user;

    @Setter
    @Getter
    @Id

    private long id;
}

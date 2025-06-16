package com.example.planify.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GroupChat {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL,  orphanRemoval = true)
    @JsonManagedReference
    private List<Message> messages;

    @ManyToMany(mappedBy = "groupChats")
    @JsonIgnore
    private List<User> users;

}

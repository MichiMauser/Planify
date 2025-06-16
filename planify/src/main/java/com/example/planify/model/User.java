package com.example.planify.model;
import com.example.planify.constans.Gender;
import com.example.planify.validators.PasswordCheck;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer age;
    private Gender gender;
    private String role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,  orphanRemoval = true)
    @JsonManagedReference
    private List<Notebook> notebooks;

    @ManyToMany
    @JoinTable(
            name = "user_groupchat",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "groupchat_id")
    )
    @JsonIgnore
    private List<GroupChat> groupChats;

//    private List<Notebook> notebooks;
//    private List<Notebook> archiveNotebooks;
//    private List<Achievement> achievements;

}
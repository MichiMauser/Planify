package com.example.planify.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SmartNotebook extends Notebook{
    @ElementCollection
    private List<String> response;
}

package com.gt.gestion_taches.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Division extends User {

    private String nameDivision;

    @OneToMany(mappedBy = "division",cascade = CascadeType.ALL)
    private Collection<Task> tasks;
}

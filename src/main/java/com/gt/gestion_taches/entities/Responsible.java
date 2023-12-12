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
public class Responsible extends User {

    @OneToMany(mappedBy = "responsible",cascade = CascadeType.ALL)
    private Collection<Agenda> agendas;
}

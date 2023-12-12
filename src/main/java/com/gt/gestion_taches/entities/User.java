package com.gt.gestion_taches.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String cni;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserAccount userAccount;
}

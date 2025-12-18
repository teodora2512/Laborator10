package com.example.Laborator10.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


@Entity
@Table(name = "carti")
@Data // Generează gettere, settere, toString, equals, hashCode
@NoArgsConstructor // Constructor implicit
@AllArgsConstructor // Constructor cu toți parametrii
public class Carte {
    @Id
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "titlul")
    private String titlul;
    @Column(name = "autorul")
    private String autorul;
}

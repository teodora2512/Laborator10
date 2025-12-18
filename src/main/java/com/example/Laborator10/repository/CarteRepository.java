package com.example.Laborator10.repository;

import com.example.Laborator10.entity.Carte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarteRepository extends JpaRepository<Carte,String> {
    List<Carte> findByAutorul(String autorul);

    // Aceste metode există deja în JpaRepository:
    // existsById(String isbn) ✓
    // findById(String isbn) ✓
    // deleteById(String isbn) ✓

}

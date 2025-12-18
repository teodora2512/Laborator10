package com.example.Laborator10.controller;

import com.example.Laborator10.entity.Carte;
import com.example.Laborator10.repository.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CarteWebController {

    @Autowired
    CarteRepository repository;

    @GetMapping("/lista-carti")
    public String getListaCarti(Model model) {
        String s = "Lista cartilor preluate prin repository";
        model.addAttribute("str", s);
        model.addAttribute("lista", repository.findAll());
        return "carti";
    }

    @PostMapping("/operatii")
    public String operatiiCarte(
            @RequestParam(name = "isbn", required = false) String isbn,
            @RequestParam(name = "titlul", required = false) String titlul,
            @RequestParam(name = "autorul", required = false) String autorul,

            @RequestParam(name = "adauga", required = false) String adauga,
            @RequestParam(name = "modifica", required = false) String modifica,
            @RequestParam(name = "sterge", required = false) String sterge,
            @RequestParam(name = "filtreaza", required = false) String filtreaza,
            Model model) {

        String mesaj = "";
        List<Carte> listaCarti;

        // OPERAȚIA DE ADAUGARE
        if (adauga != null) {
            if (isbn == null || isbn.trim().isEmpty() ||
                    titlul == null || titlul.trim().isEmpty() ||
                    autorul == null || autorul.trim().isEmpty()) {
                mesaj = "Adaugarea nu se realizează dacă nu completați toate caracteristicile!";
            } else {
                if (repository.existsById(isbn)) {
                    mesaj = "Cartea cu ISBN-ul " + isbn + " exista deja!";
                } else {
                    Carte carte = new Carte(isbn, titlul, autorul);
                    repository.save(carte);
                    mesaj = "Adaugare realizata cu succes!";
                }
            }
            listaCarti = repository.findAll();
        }
        // OPERAȚIA DE MODIFICARE
        else if (modifica != null) {
            if (isbn == null || isbn.trim().isEmpty()) {
                mesaj = "Introduceți ISBN-ul cartii de modificat!";
            } else {
                Optional<Carte> carteOpt = repository.findById(isbn);
                if (!carteOpt.isPresent()) {
                    mesaj = "Nu se găsește nici o carte cu ISBN-ul " + isbn;
                } else {
                    Carte carte = carteOpt.get();
                    if (titlul != null && !titlul.trim().isEmpty()) {
                        carte.setTitlul(titlul);
                    }
                    if (autorul != null && !autorul.trim().isEmpty()) {
                        carte.setAutorul(autorul);
                    }
                    repository.save(carte);
                    mesaj = "Cartea cu ISBN-ul " + isbn + " a fost modificata!";
                }
            }
            listaCarti = repository.findAll();
        }
        // OPERAȚIA DE ȘTERGERE
        else if (sterge != null) {
            if (isbn == null || isbn.trim().isEmpty()) {
                mesaj = "Introduceți ISBN-ul cărții de șters!";
            } else {
                if (repository.existsById(isbn)) {
                    repository.deleteById(isbn);
                    mesaj = "Cartea cu ISBN-ul " + isbn + " a fost stearsa!";
                } else {
                    mesaj = "Nu se găsește nici o carte cu ISBN-ul " + isbn;
                }
            }
            listaCarti = repository.findAll();
        }
        // OPERAȚIA DE FILTRARE
        else if (filtreaza != null) {
            if (autorul == null || autorul.trim().isEmpty()) {
                mesaj = "Lista tuturor cărților";
                listaCarti = repository.findAll();
            } else {
                listaCarti = repository.findByAutorul(autorul);
                if (listaCarti.isEmpty()) {
                    mesaj = "Nu exista carti pentru autorul " + autorul;
                } else {
                    mesaj = "Carțile urmatoare aparțin autorului " + autorul;
                }
            }
        }
        // Dacq nicio operație nu a fost selectată
        else {
            mesaj = "Lista cartilor preluate prin repository";
            listaCarti = repository.findAll();
        }

        model.addAttribute("str", mesaj);
        model.addAttribute("lista", listaCarti);
        // Păstrăm valorile în formular (opțional)
        model.addAttribute("isbnParam", isbn != null ? isbn : "");
        model.addAttribute("titlulParam", titlul != null ? titlul : "");
        model.addAttribute("autorulParam", autorul != null ? autorul : "");
        return "carti";
    }
}
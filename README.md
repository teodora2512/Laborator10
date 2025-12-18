# Laborator 10 – Aplicație Spring Boot pentru gestiunea cărților

## 📌 Descriere
Această aplicație web a fost realizată în cadrul **Laboratorului 10** și implementează operațiile **CRUD** (Create, Read, Update, Delete) asupra unei tabele de cărți, folosind **Spring Boot**, **Spring Data JPA** și **Thymeleaf**.

Aplicația permite:
- afișarea listei de cărți
- adăugarea unei cărți noi
- modificarea unei cărți existente
- ștergerea unei cărți
- filtrarea cărților după autor

---

## 🛠️ Tehnologii utilizate
- Java
- Spring Boot
- Spring Data JPA
- Thymeleaf
- Hibernate
- Maven
- Bootstrap 5
- MySQL / H2 (în funcție de configurare)

---

## 📂 Structura proiectului
Laborator10
│
├── src/main/java/com/example/Laborator10
│ ├── controller
│ │ └── CarteWebController.java
│ ├── entity
│ │ └── Carte.java
│ ├── repository
│ │ └── CarteRepository.java
│ └── Laborator10Application.java
│
├── src/main/resources
│ ├── templates
│ │ └── carti.html
│ └── application.properties
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md

---

## 🗃️ Modelul de date
### Entitatea `Carte`
| Câmp     | Tip     | Descriere              |
|--------|--------|------------------------|
| isbn   | String | Cheie primară          |
| titlul | String | Titlul cărții          |
| autorul| String | Autorul cărții         |

---

## 🌐 Funcționalități
- **GET `/lista-carti`**
    - Afișează lista tuturor cărților din baza de date

- **POST `/operatii`**
    - `Adauga` – adaugă o carte nouă
    - `Modifica` – modifică titlul și/sau autorul unei cărți
    - `Sterge` – șterge o carte după ISBN
    - `Filtreaza` – afișează cărțile unui anumit autor

---

## 🖥️ Interfața grafică
- Realizată cu **Thymeleaf** și **Bootstrap**
- Formular unic pentru toate operațiile
- Mesaje informative afișate utilizatorului
- Tabel pentru afișarea cărților existente

---

## 🧪 Date de test (SQL)
```sql
insert into carti(isbn, autorul, titlul)
values ("ISBN1","Yuval Noah Harari","Scurta istorie a omenirii");

insert into carti(isbn, autorul, titlul)
values ("ISBN2","Yuval Noah Harari","Homo Deus - Scurta istorie a viitorului");

insert into carti(isbn, autorul, titlul)
values ("ISBN3","J.D. Salinger","De veghe in lanul de secara");

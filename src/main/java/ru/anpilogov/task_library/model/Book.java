package ru.anpilogov.task_library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "book")
public class Book {
    @Id
    @Column
    private Long id; // Isbn
    @Column
    private String title;

    @JoinColumn(name = "id_genre")
    @OneToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Genre genre;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Author author;
}
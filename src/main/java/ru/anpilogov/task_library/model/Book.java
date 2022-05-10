package ru.anpilogov.task_library.model;

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
    private Genre genre;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")

    private Author author;


}
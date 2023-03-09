package ru.anpilogov.task_library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.Random;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String patronymic;
    @Column(columnDefinition = "DATE")
    private Date dateOfBirth;

    public void generateId() {
        this.id = new Random().nextLong();
    }
}





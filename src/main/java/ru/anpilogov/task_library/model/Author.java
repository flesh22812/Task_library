package ru.anpilogov.task_library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "author")
public class Author {
    @Id

    private Long id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String patronymic;
    @Column (columnDefinition="DATE")
    private Calendar dateOfBirth;



}





package ru.anpilogov.task_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.anpilogov.task_library.model.Author;

import java.util.List;


public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a WHERE (LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%')) OR :name IS NULL OR :name = '') " +
            "AND (LOWER(a.surname) LIKE LOWER(:surname) OR :surname IS NULL OR :surname = '') " +
            "AND (LOWER(a.patronymic) LIKE LOWER(:patronymic) OR :patronymic IS NULL OR :patronymic = '')")
    List<Author> findAllByNameContaints(String name, String surname, String patronymic);


}
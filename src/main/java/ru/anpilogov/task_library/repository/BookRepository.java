package ru.anpilogov.task_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anpilogov.task_library.model.Book;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAuthor_SurnameAndGenre_GenreName(String author_surname, String genre_genreName);

}

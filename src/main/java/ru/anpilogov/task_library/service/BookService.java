package ru.anpilogov.task_library.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.anpilogov.task_library.model.Book;
import ru.anpilogov.task_library.model.DTO;
import ru.anpilogov.task_library.repository.AuthorRepository;
import ru.anpilogov.task_library.repository.BookRepository;
import ru.anpilogov.task_library.repository.GenreRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service

public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(Long id) {

        return bookRepository.findById(id);


    }

    public Book addBook(DTO.BookDTO node) {
     Random r = new Random();
        Book book = new Book();
        book.setId((long) r.nextInt(1000));
        book.setTitle(node.getTitle());
        book.setAuthor(authorRepository.getById(node.getAuthorId()));
        book.setGenre(genreRepository.getById(node.getGenreId()));
        return bookRepository.save(book);
    }

    public Book updateBook(Long ISBN, DTO.BookDTO node) {
        Book book = bookRepository.findById(ISBN).get();
        book.setTitle(node.getTitle());
        book.setAuthor(authorRepository.getById(node.getAuthorId()));
        book.setGenre(genreRepository.getById(node.getGenreId()));
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findByFilter(String surname, String genre_name) {


        return bookRepository.findAllByAuthor_SurnameAndGenre_GenreName(surname, genre_name);
    }

    public Page<Book> findAllPages(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return bookRepository.findAll(pageable);
    }
}

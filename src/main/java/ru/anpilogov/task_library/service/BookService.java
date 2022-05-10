package ru.anpilogov.task_library.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.anpilogov.task_library.model.Book;
import ru.anpilogov.task_library.repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(Long id) {

        return bookRepository.findById(id);


    }

    public Book addBook(Book book) {
        if (bookRepository.findById(book.getId()).isPresent()) {
            Random r = new Random();
            book.setId(r.nextLong());
        }
        return bookRepository.save(book);
    }

    public Book updateBook(Long ISBN, Book book) {
        book.setId(ISBN);
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

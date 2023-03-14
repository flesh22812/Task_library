package ru.anpilogov.task_library.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.anpilogov.task_library.model.Book;
import ru.anpilogov.task_library.model.DTO;
import ru.anpilogov.task_library.service.BookService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("library/v1")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PutMapping("/book")
    @Operation(summary = "Добавление записи книги ", description = "Позволяет добавить запись по переданному json ")
    public Book saveBook(@RequestBody DTO.BookDTO book) {

        return bookService.addBook(book);

    }

    @PatchMapping("/book/{id}")
    @Operation(summary = "Обновление записи автора по id ", description = "Позволяет обновить запись по полученному из ссылки id ")
    public Book updateBook(@PathVariable Long id, @RequestBody  DTO.BookDTO node) {

        return bookService.updateBook(id, node);
    }

    @DeleteMapping("/book/{id}")
    @Operation(summary = "Удаление записи книги по переданному id", description = "Позволяет удалить запись по id (Мягкое удаление)")
    public void deleteBook(@PathVariable @Parameter(description = "id книги") Long id) {
        bookService.deleteBook(id);

    }

    @GetMapping("books")
    @Operation(summary = "Вывод всех книг")
    public ResponseEntity getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping("/book/{id}")
    @Operation(summary = "Получение записи книги по id ", description = "Позволяет получить книгу по полученному параметру id")
    public Optional<Book> findBookById(@PathVariable @Parameter(description = "id книги") Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping("/book")
    @Operation(summary = "Получение записи книги с помощью фильтра ", description = "Позволяет получить книгу по полученным параметрам: фамилии автора и названию жанра")
    public List<Book> findAuthorByName(@RequestParam @Parameter(description = "Фамилия автора") String surname, @RequestParam @Parameter(description = "Название жанра") String genreName) {
        return bookService.findByFilter(surname, genreName);
    }

    @GetMapping("/book/pages")
    @Operation(summary = "Получение страниц записи книг", description = "Позволяет получить страницы записи книг по полученным параметрам: страница, размер, параметр для сортировки")

    public Page<Book> findAllPagesBooks(@RequestParam("page") @Parameter(description = "Номер страницы") int page,
                                        @RequestParam("size") @Parameter(description = "Размер страницы") int size, @RequestParam("sort") @Parameter(description = "Параметр для сортировки") String sort) {
        return bookService.findAllPages(page, size, sort);
    }
}

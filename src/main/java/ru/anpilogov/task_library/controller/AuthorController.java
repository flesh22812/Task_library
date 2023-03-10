package ru.anpilogov.task_library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.anpilogov.task_library.model.Author;
import ru.anpilogov.task_library.service.AuthorService;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("library/v1")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PutMapping("/author")// ссылки
    @Operation(summary = "Добавление записи автора ", description = "Позволяет добавить запись по переданному json ")
    public Author saveAuthor(@RequestBody Author author) {
        author.generateId();
        return authorService.addAuthor(author);

    }

    @PatchMapping("/author/{id}")
    @Operation(summary = "Обновление записи автора по переданному id", description = "Позволяет обновить запись по полученному из ссылки id ")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author author) {

        return authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/author/{id}")
    @Operation(summary = "Удаление записи автора по переданному id", description = "Позволяет удалить запись по id")
    public void deleteAuthor(@PathVariable @Parameter(description = "id книги") Long id) {
        authorService.deleteAuthor(id);

    }

    @GetMapping("/authors")
    @Operation(summary = "Вывод всех авторов")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/authors/filter")
    @Operation(summary = "Вывод всех авторов")
    public List<Author> getAllAuthorsFilter(@RequestParam(required = false) String name,
                                            @RequestParam(required = false) String surname,
                                            @RequestParam(required = false) String patronymic) {
        return authorService.getAllAuthorsFilter( name,  surname,  patronymic);
    }

    @GetMapping("/author")
    @Operation(summary = "Получение записи автора по id ", description = "Позволяет получить автора по полученному параметру id")
    public Optional<Author> getAuthorsById(@RequestParam @Parameter(description = "id книги") Long id) {
        return authorService.getAuthorById(id);
    }


    @GetMapping("/author/pages")
    public Page<Author> findAllPagesAuthors(@RequestParam("page") @Parameter(description = "Номер страницы") int page,
                                            @RequestParam("size") @Parameter(description = "Размер страницы") int size, @RequestParam("sort") @Parameter(description = "Параметр для сортировки") String sort) {
        return authorService.findAllPagesAuthors(page, size, sort);
    }
}

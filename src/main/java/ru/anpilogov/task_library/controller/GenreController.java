package ru.anpilogov.task_library.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.anpilogov.task_library.model.Genre;
import ru.anpilogov.task_library.service.GenreService;

import java.util.Optional;

@RestController
@RequestMapping("library/v1")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genre")
    @Operation(summary = "Вывод всех жанров")
    public ResponseEntity getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @PostMapping("/genre")
    @Operation(summary = "Получение записи жанра по id ", description = "Позволяет получить жанр по полученному параметру id")
    public Optional<Genre> findGenreById(@RequestParam Long id) {
        return genreService.findGenreById(id);
    }

    @PutMapping("/genre")
    @Operation(summary = "Добавление записи жанра ", description = "Позволяет добавить запись по переданному json ")
    public Genre saveBook(@RequestBody Genre genre) {

        return genreService.addGenre(genre);

    }
}

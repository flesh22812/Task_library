package ru.anpilogov.task_library.service;

import org.springframework.stereotype.Service;
import ru.anpilogov.task_library.model.Genre;
import ru.anpilogov.task_library.repository.GenreRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service

public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();

    }

    public Optional<Genre> findGenreById(Long id) {

        return genreRepository.findById(id);

    }

    public Genre addGenre(Genre genre) {
        if (genreRepository.findById(genre.getId_genre()).isPresent()) {
            Random r = new Random();
            genre.setId_genre((long) r.nextInt(100));
        }
        return genreRepository.save(genre);
    }
}

package ru.anpilogov.task_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anpilogov.task_library.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}

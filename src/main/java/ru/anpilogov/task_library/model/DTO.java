package ru.anpilogov.task_library.model;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

public class DTO {


        @Data
        public static class BookDTO {
            @NotNull
            private String title;
            @NotNull
            private Long genreId;
            @NotNull
            private Long authorId;
        }
    }




package ru.anpilogov.task_library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@EnableWebSecurity
@SpringBootApplication

public class TaskLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskLibraryApplication.class, args);

    }

}


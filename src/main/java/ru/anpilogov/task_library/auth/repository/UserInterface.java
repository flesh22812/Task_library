package ru.anpilogov.task_library.auth.repository;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.anpilogov.task_library.auth.UserRegistrationDto;
import ru.anpilogov.task_library.auth.model.Account;


public interface UserInterface extends UserDetailsService {

    Account findByEmail(String email);

    Account save(UserRegistrationDto registration);
}

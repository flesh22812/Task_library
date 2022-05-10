package ru.anpilogov.task_library.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anpilogov.task_library.auth.model.Account;

@Repository
public interface UserRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
}

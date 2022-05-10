package ru.anpilogov.task_library.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.anpilogov.task_library.auth.model.Account;
import ru.anpilogov.task_library.auth.model.Role;
import ru.anpilogov.task_library.auth.repository.UserInterface;
import ru.anpilogov.task_library.auth.repository.UserRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Account findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Account save(UserRegistrationDto registration) {
        Account account = new Account();
        account.setFirst_name(registration.getFirstName());
        account.setLast_name(registration.getLastName());
        account.setEmail(registration.getEmail());
        account.setPassword(passwordEncoder.encode(registration.getPassword()));
        if (!Objects.equals(registration.getFirstName(), "admin")) {
            account.setRoles(Arrays.asList(new Role("ROLE_USER")));
        } else {
            account.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
        }
        return userRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = userRepository.findByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(account.getEmail(),
                account.getPassword(),
                mapRolesToAuthorities(account.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}

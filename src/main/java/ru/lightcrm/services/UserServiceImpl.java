package ru.lightcrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.Priority;
import ru.lightcrm.entities.User;
import ru.lightcrm.exceptions.ResourceNotFoundException;
import ru.lightcrm.repositories.UsersRepository;
import ru.lightcrm.services.interfaces.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;

    @Override
    public Optional<User> getByUsername(String username) {
        return usersRepository.findByLogin(username);
    }

    @Override
    public void saveUser(User user) {
        usersRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByUsername(username).orElseThrow(() -> new ResourceNotFoundException(String.format("Пользователь '%s' не найден", username)));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthorities(user));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return getGrantedAuthorities(getPriorities(user));
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(List<String> priorities) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String priority : priorities) {
            authorities.add(new SimpleGrantedAuthority(priority));
        }
        return authorities;
    }

    private List<String> getPriorities(User user) {
        List<String> priorities = new ArrayList<>();
        List<Priority> collection = user.getPriorities();
        //TODO доделать получение priority через профиль -> штатную единицу -> роли
//        Collection<Role> roles =
        for (Priority priority : collection) {
            priorities.add(priority.getName());
        }
        return priorities;
    }


}

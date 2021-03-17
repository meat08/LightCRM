package ru.lightcrm.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.lightcrm.entities.Priority;
import ru.lightcrm.entities.Profile;
import ru.lightcrm.entities.Role;
import ru.lightcrm.entities.User;
import ru.lightcrm.exceptions.ResourceNotFoundException;
import ru.lightcrm.repositories.ProfileRepository;
import ru.lightcrm.repositories.UsersRepository;
import ru.lightcrm.services.interfaces.UserService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final ProfileRepository profileRepository;

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

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(Set<String> priorities) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String priority : priorities) {
            authorities.add(new SimpleGrantedAuthority(priority));
        }
        return authorities;
    }

    private Set<String> getPriorities(User user) {
        Set<String> priorities = new HashSet<>();
        Set<Priority> collection = user.getPriorities();
        Profile profile = profileRepository.findByLogin(user.getLogin())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Профиль пользователя '%s' не найден", user.getLogin())));
        Collection<Role> roles = profile.getStaffUnit().getRoles();
        for (Role role : roles) {
            collection.addAll(role.getPriorities());
        }
        for (Priority priority : collection) {
            priorities.add(priority.getName());
        }
        return priorities;
    }


}

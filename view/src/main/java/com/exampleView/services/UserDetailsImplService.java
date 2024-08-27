package com.exampleView.services;

import com.exampleLogic.models.User;
import com.exampleLogic.repositories.UserRepository;
import com.exampleView.authentication.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImplService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Ваш репозиторий для доступа к данным

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username); // Найдите пользователя по имени

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new UserDetailsImpl(user.getId(), user.getUsername(), user.getPassword(),
                user.getAuthorities());
    }
}
package com.exampleView.services;

import com.exampleLogic.exceptions.ObjectNotFoundException;
import com.exampleLogic.models.User;
import com.exampleLogic.repositories.CatRepository;
import com.exampleLogic.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserRepository getuserRepository() {
        return userRepository;
    }

    public Optional<User> getuserById(Long id) throws ObjectNotFoundException {
        return userRepository.findById(id);
    }

    public void createuser(User user) {
        userRepository.save(user);
    }

    public void updateuser(User user) {
        userRepository.save(user);
    }

    public void deleteuser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get(); // Получаем объект user
            userRepository.delete(user); // Удаляем кота
        } else {
            throw new RuntimeException("user not found with id: " + id);
        }
    }
}

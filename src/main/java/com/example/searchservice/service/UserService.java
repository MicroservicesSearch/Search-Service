package com.example.searchservice.service;

import com.example.searchservice.document.User;
import com.example.searchservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(final User user){userRepository.save(user);
    }

    public User findById(final Long id){
        return userRepository.findById(id).orElse(null);
    }

}

package com.example.searchservice.service;

import com.example.searchservice.document.User;
import com.example.searchservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> save(final User user){
       return userRepository.save(user);
    }

    public Mono<User> findById(final String id){
        return userRepository.findById(id);
    }

    public Flux<User> findAll(){
        return userRepository.findAll();
    }

}

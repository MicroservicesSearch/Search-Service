package com.example.searchservice.controller;

import com.example.searchservice.document.User;
import com.example.searchservice.search.SearchRequestDTO;
import com.example.searchservice.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public void save(@RequestBody final User user){
         userService.save(user);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable final Long id){
        return userService.findById(id);
    }

    @PostMapping("/search")
    public List<User> search(@RequestBody final SearchRequestDTO dto) {
        return userService.search(dto);
    }


}

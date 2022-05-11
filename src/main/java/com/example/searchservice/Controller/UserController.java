package com.example.searchservice.Controller;

import com.example.searchservice.document.User;
import com.example.searchservice.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public Mono<User> save(@RequestBody final User user){
        return userService.save(user);
    }

    @GetMapping("/{id}")
    public Mono<User> findById(@PathVariable("id") final String id){
        return userService.findById(id);
    }

    @GetMapping("/all")
    public Flux<User> findAll(){
        return userService.findAll();
    }

}

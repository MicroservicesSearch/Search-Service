package com.example.searchservice.controller;

import com.example.searchservice.document.Post;
import com.example.searchservice.search.SearchRequestDTO;
import com.example.searchservice.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping ("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public void save(@RequestBody final Post post){
        postService.save(post);
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable final Long id){
        return postService.findById(id);
    }

    @PostMapping("/search")
    public List<Post> search(@RequestBody final SearchRequestDTO dto) {
        return postService.search(dto);
    }


}

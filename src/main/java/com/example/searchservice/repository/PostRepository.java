package com.example.searchservice.repository;

import com.example.searchservice.document.Post;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostRepository extends ElasticsearchRepository<Post, Long>{
}

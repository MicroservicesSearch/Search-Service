package com.example.searchservice.repository;

import com.example.searchservice.document.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User, Long> {
}

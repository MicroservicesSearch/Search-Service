package com.example.searchservice.service;

import com.example.searchservice.document.Post;
import com.example.searchservice.document.User;
import com.example.searchservice.document.helper.Indices;
import com.example.searchservice.repository.PostRepository;
import com.example.searchservice.repository.UserRepository;
import com.example.searchservice.search.SearchRequestDTO;
import com.example.searchservice.search.util.SearchUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PostService {


    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final PostRepository postRepository;
    private final RestHighLevelClient client;

    @Autowired
    public PostService(PostRepository postRepository, RestHighLevelClient client) {
        this.postRepository = postRepository;
        this.client = client;
    }

    public List<Post> search(final SearchRequestDTO dto) {
        final SearchRequest request = SearchUtil.buildSearchRequest(
                Indices.POST_INDEX,
                dto
        );

        if (request == null) {
            LOG.error("Failed to build search request");
            return Collections.emptyList();
        }

        try {
            final SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            final SearchHit[] searchHits = response.getHits().getHits();
            final List<Post> posts = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                posts.add(
                        MAPPER.readValue(hit.getSourceAsString(), Post.class)
                );
            }

            return posts;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public void save(final Post post){
        postRepository.save(post);
    }

    public Post findById(final Long id){
        return postRepository.findById(id).orElse(null);
    }
}

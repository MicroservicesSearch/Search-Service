package com.example.searchservice.service;

import com.example.searchservice.document.User;
import com.example.searchservice.document.helper.Indices;
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
public class UserService {


    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RestHighLevelClient client;

    @Autowired
    public UserService(UserRepository userRepository, RestHighLevelClient client) {
        this.userRepository = userRepository;
        this.client = client;
    }

    public List<User> search(final SearchRequestDTO dto) {
        final SearchRequest request = SearchUtil.buildSearchRequest(
                Indices.USER_INDEX,
                dto
        );

        if (request == null) {
            LOG.error("Failed to build search request");
            return Collections.emptyList();
        }

        try {
            final SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            final SearchHit[] searchHits = response.getHits().getHits();
            final List<User> users = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                users.add(
                        MAPPER.readValue(hit.getSourceAsString(), User.class)
                );
            }

            return users;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public void save(final User user){userRepository.save(user);
    }

    public User findById(final Long id){
        return userRepository.findById(id).orElse(null);
    }

}

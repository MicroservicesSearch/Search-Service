package com.example.searchservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients;
import org.springframework.data.elasticsearch.config.AbstractReactiveElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

@Configuration
@EnableReactiveElasticsearchRepositories(basePackages = "com.example.searchservice.repository")
@ComponentScan(basePackages = {"com.example.searchservice"})
public class Config extends AbstractReactiveElasticsearchConfiguration {

    @Value("${elasticsearch.url}")
    public String elasticUrl;


    @Override
    @Bean
    public ReactiveElasticsearchClient reactiveElasticsearchClient() {
        final ClientConfiguration configuration = ClientConfiguration.builder()
                .connectedTo(elasticUrl)
                .build();
        return ReactiveRestClients.create(configuration);
    }
}

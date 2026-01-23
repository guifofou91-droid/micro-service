package com.example.video_streaming_service.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfiguration {

    @LoadBalanced
    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }
}

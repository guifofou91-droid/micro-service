package com.example.video_streaming_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieCatalogueService {

    private static final String CATALOG_SERVICE = "http://movie-catalog-service";

    private final RestClient restClient;

    public MovieCatalogueService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String getMoviePath(Long movieInfoId) {
        return restClient.get()
                .uri(CATALOG_SERVICE + "/movie-infos/"+ movieInfoId.toString())
                .retrieve()
                .body(String.class);
    }
}

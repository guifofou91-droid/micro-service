package com.example.video_streaming_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieCatalogueService {

    private static final String CATALOG_SERVICE = "http://movie-catalog-service";

    @Autowired
    private RestTemplate restTemplate;

    public String getMoviePath(Long movieInfoId) {
        var reponse = restTemplate.getForEntity(CATALOG_SERVICE + "/movie-info/find-path-by-id/{movieinfoId}", String.class, movieInfoId);
        return reponse.getBody();
    }
}

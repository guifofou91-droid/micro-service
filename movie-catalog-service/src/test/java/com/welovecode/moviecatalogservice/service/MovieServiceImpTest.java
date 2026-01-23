package com.welovecode.moviecatalogservice.service;

import com.welovecode.moviecatalogservice.model.MovieInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.mockito.Mockito.mock;

@Testcontainers
class MovieServiceImpTest {

    @Container
    @ServiceConnection
    static MySQLContainer mysql = new MySQLContainer("mysql:latest");


    private IMoviService moviService;

    public void setup() {
        moviService = mock(MovieServiceImp.class);
    }

    @Test
    public void shouldCreateMovie() {
        // Given
        MovieInfo movieInfo = new MovieInfo("Ironman", "Description Iron man", "F://movies/action");

        // When
        MovieInfo movieInfoSave = moviService.saveMovie(movieInfo);

        // Then
        Assertions.assertNotNull(movieInfoSave);
        Assertions.assertNotNull(movieInfoSave.getId());
        Assertions.assertEquals(movieInfoSave.getName(), movieInfo.getName());
        Assertions.assertEquals(movieInfoSave.getDescription(), movieInfo.getDescription());
        Assertions.assertEquals(movieInfoSave.getPath(), movieInfo.getPath());
    }
}
package com.welovecode.moviecatalogservice.repository;

import com.welovecode.moviecatalogservice.model.MovieInfo;
import com.welovecode.moviecatalogservice.repository.config.MySQLDBTestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;


@DataJpaTest
@Import(MySQLDBTestConfig.class)   // Import the configuration
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieInfoRepositoryIntegrationTest {

    @Autowired
    private MovieInfoRepository movieInfoRepository;

    @BeforeEach
    void setup() {
        movieInfoRepository.deleteAll();  // Clears all previous data before each test
    }

    @Test
    public void shouldSaveMovieInfoSuccessfully(){
        //Given
        MovieInfo movieInfoToSave = new MovieInfo("Ironman", "Description Iron man", "F://movies/action");
        //When
        MovieInfo movieInfoSave = movieInfoRepository.save(movieInfoToSave);
        //Then
        Assertions.assertNotNull(movieInfoSave);
        Assertions.assertNotNull(movieInfoSave.getId());
    }
}

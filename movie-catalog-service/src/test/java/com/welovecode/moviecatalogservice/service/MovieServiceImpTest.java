package com.welovecode.moviecatalogservice.service;

import com.welovecode.moviecatalogservice.model.MovieInfo;
import com.welovecode.moviecatalogservice.repository.MovieInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MovieServiceImpTest {


    @Mock
    private MovieInfoRepository movieInfoRepository;

    @InjectMocks
    private MovieServiceImp moviService;

    @Test
    public void shouldCreateMovieSuccessfully() {
        // Given
        MovieInfo movieInfoSave = new MovieInfo(UUID.randomUUID(),"Ironman", "Description Iron man", "F://movies/action");
        MovieInfo movieInfoToSave = new MovieInfo("Ironman", "Description Iron man", "F://movies/action");
        when(movieInfoRepository.save(any(MovieInfo.class))).thenReturn(movieInfoSave);
        // When
        MovieInfo movieInfo = moviService.saveMovie(movieInfoToSave);

        // Then
        Assertions.assertNotNull(movieInfo);
        Assertions.assertNotNull(movieInfo.getId());
        Assertions.assertEquals(movieInfo.getName(), movieInfoToSave.getName());
        Assertions.assertEquals(movieInfo.getDescription(), movieInfoToSave.getDescription());
        Assertions.assertEquals(movieInfo.getPath(), movieInfoToSave.getPath());

        verify(movieInfoRepository).save(any(MovieInfo.class));
    }
}
package com.welovecode.moviecatalogservice.service;

import com.welovecode.moviecatalogservice.model.MovieInfo;
import com.welovecode.moviecatalogservice.repository.MovieInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieServiceImp implements IMoviService {

    private final MovieInfoRepository repository;

    public MovieServiceImp(MovieInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public MovieInfo saveMovie(MovieInfo movieInfo) {
        return repository.save(movieInfo);
    }

    @Override
    public List<MovieInfo> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MovieInfo> getById(UUID movieInfoId) {
        return repository.findById(movieInfoId);
    }
}

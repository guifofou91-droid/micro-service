package com.welovecode.moviecatalogservice.service;

import com.welovecode.moviecatalogservice.model.MovieInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IMoviService {
    public MovieInfo saveMovie(MovieInfo movieInfo);
    public List<MovieInfo> getAll();
    public Optional<MovieInfo> getById(UUID movieInfoId);
}

package com.welovecode.movie_catalog_service.controller;

import com.welovecode.movie_catalog_service.model.MovieInfo;
import com.welovecode.movie_catalog_service.repository.MovieInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieInfoController {

    @Autowired
    private MovieInfoRepository repository;

    @PostMapping("/movie-info/save")
    public List<MovieInfo> saveAll(@RequestBody List<MovieInfo> movieInfos){
        return repository.saveAll(movieInfos);
    }

    @GetMapping("/movie-info/list")
    public List<MovieInfo> getAll(){
        return repository.findAll();
    }

    @GetMapping("/movie-info/find-path-by-id/{movieinfoId}")
    public String findPathById(@PathVariable Long movieinfoId){
        var videoInfoOptional = repository.findById(movieinfoId);
        return videoInfoOptional.map(MovieInfo::getPath).orElse(null);
    }
}

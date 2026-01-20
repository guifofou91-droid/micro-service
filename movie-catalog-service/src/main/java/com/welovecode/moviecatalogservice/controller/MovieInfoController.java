package com.welovecode.moviecatalogservice.controller;

import com.welovecode.moviecatalogservice.model.MovieInfo;
import com.welovecode.moviecatalogservice.repository.MovieInfoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie-infos")
public class MovieInfoController {

    private MovieInfoRepository repository;

    public MovieInfoController(MovieInfoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/save")
    public List<MovieInfo> saveAll(@RequestBody List<MovieInfo> movieInfos){
        return repository.saveAll(movieInfos);
    }

    @GetMapping("/list")
    public List<MovieInfo> getAll(){
        return repository.findAll();
    }

    @GetMapping("/{movieinfoId}")
    public String findPathById(@PathVariable Long movieinfoId){
        Optional<MovieInfo> movieInfo = repository.findById(movieinfoId);
        String path;
        if (movieInfo.isPresent()){
            MovieInfo movieInfoGet = movieInfo.get();
            return movieInfoGet.getPath();
        }
        return null;
    }
}

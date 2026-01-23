package com.welovecode.moviecatalogservice.controller;

import com.welovecode.moviecatalogservice.exception.ResourceNotFoundException;
import com.welovecode.moviecatalogservice.model.MovieInfo;
import com.welovecode.moviecatalogservice.repository.MovieInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie-infos")
public class MovieInfoController {

    private static final Logger logger = LoggerFactory.getLogger(MovieInfoController.class);

    private final MovieInfoRepository repository;

    public MovieInfoController(MovieInfoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/save")
    public ResponseEntity<MovieInfo> save(@RequestBody @Validated MovieInfo movieInfo){
        logger.info("CREATE MOVIE");
        return new ResponseEntity<>(repository.save(movieInfo), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<MovieInfo>> getAll(){
        logger.info("GET ALL MOVIES");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{movieInfoId}")
    public ResponseEntity<String> findPathById(@PathVariable Long movieInfoId){
        logger.info("GET MOVIE BY ID: {}", movieInfoId);
        Optional<MovieInfo> movieOptional = repository.findById(movieInfoId);
        return movieOptional
                .map(movie -> ResponseEntity.ok(movie.getPath()))
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with Id "+movieInfoId));
    }
}

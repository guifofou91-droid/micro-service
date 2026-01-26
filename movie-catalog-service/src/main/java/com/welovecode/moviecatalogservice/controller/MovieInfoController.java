package com.welovecode.moviecatalogservice.controller;

import com.welovecode.moviecatalogservice.exception.ResourceNotFoundException;
import com.welovecode.moviecatalogservice.model.MovieInfo;
import com.welovecode.moviecatalogservice.service.IMoviService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/movie-infos")
public class MovieInfoController {


    private final IMoviService service;

    public MovieInfoController(IMoviService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<MovieInfo> save(@RequestBody MovieInfo movieInfo){
        log.info("CREATE MOVIE");
        return new ResponseEntity<>(service.saveMovie(movieInfo), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<MovieInfo>> getAll(){
        log.info("GET ALL MOVIES");
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{movieInfoId}")
    public ResponseEntity<String> findPathById(@PathVariable UUID movieInfoId){
        log.info("GET MOVIE BY ID: {}", movieInfoId);
        Optional<MovieInfo> movieOptional = service.getById(movieInfoId);
        return movieOptional
                .map(movie -> ResponseEntity.ok(movie.getPath()))
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with Id "+movieInfoId));
    }
}

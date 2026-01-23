package com.example.video_streaming_service.controller;

import com.example.video_streaming_service.services.MovieCatalogueService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/streaming")
public class MovieStreamingController {

    public static final Logger log = Logger.getLogger(MovieStreamingController.class.getName());
    public static final String VIDEO_DIRECTORY = "C:\\Users\\gui.a.fofou.fonzam\\Documents\\Mydocs\\MostLove\\";

    private final MovieCatalogueService movieCatalogueService;

    public MovieStreamingController(MovieCatalogueService movieCatalogueService) {
        this.movieCatalogueService = movieCatalogueService;
    }

    @GetMapping("movies/{moviePath}")
    public ResponseEntity<InputStreamResource> streamMovie(@PathVariable String moviePath) {
        File file = new File(VIDEO_DIRECTORY + moviePath);
        try {
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("video/mp4"))
                    .body(inputStreamResource);
        } catch (FileNotFoundException fileNotFoundException) {
            log.log(Level.INFO, "Path not found = {0}", file.getAbsolutePath());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("movies/{movieId}")
    public ResponseEntity<InputStreamResource> streamMovieWithID(@PathVariable Long movieId) {
        String moviePath = movieCatalogueService.getMoviePath(movieId);
        log.log(Level.INFO, "Resolved movie path = {0}", moviePath);
        return streamMovie(moviePath);
    }
}

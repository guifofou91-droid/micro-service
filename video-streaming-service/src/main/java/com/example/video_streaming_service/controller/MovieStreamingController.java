package com.example.video_streaming_service.controller;

import com.example.video_streaming_service.services.MovieCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class MovieStreamingController {

    public static final Logger log = Logger.getLogger(MovieStreamingController.class.getName());
    public static final String VIDEO_DIRECTORY = "C:\\Users\\gui.a.fofou.fonzam\\Documents\\Mydocs\\MostLove\\";

    @Autowired
    private MovieCatalogueService movieCatalogueService;

    @GetMapping("/stream/{videoPath}")
    public ResponseEntity<InputStreamResource> streamMovie(@PathVariable String videoPath) throws FileNotFoundException {
        File file = new File(VIDEO_DIRECTORY + videoPath);
        if (file.exists()){
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("video/mp4"))
                    .body(inputStreamResource);
        } else {
            System.out.println(file.getAbsolutePath());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/stream/with-id/{videoInfoId}")
    public ResponseEntity<InputStreamResource> streamMovie(@PathVariable Long videoInfoId) throws FileNotFoundException {
        String moviePath = movieCatalogueService.getMoviePath(videoInfoId);
        log.log(Level.INFO, "Resolved movie path = {0}", moviePath);
        return streamMovie(moviePath);
    }
}

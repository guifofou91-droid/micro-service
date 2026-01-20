package com.welovecode.moviecatalogservice.glue;

import com.welovecode.moviecatalogservice.model.MovieInfo;
import com.welovecode.moviecatalogservice.repository.MovieInfoRepository;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import java.util.List;
import java.util.Map;

@WebMvcTest
public class MovieStep {

    @Autowired
    private MovieInfoRepository movieInfoRepository;

    private MovieInfo movieInfo;

    private MovieInfo movieInfoSave;

    @DataTableType
    public MovieInfo assetEntry(Map<String, String> entry) {
        return MovieInfo.builder()
                .name(entry.get("name"))
                .description(entry.get("description"))
                .path(entry.get("path"))
                .build();
    }

    @Given("The user send the following information:")
    public void movieInformation(List<MovieInfo> movieInfos) {
        movieInfos = MovieInfo.builder()
                .name(movieInfos.get("name"))
                .description(movieInfos.get("description"))
                .path(movieInfos.get("path"))
                .build();
    }

    @When("The user save the movie")
    public void saveMovie() {
        movieInfoSave = movieInfoRepository.save(movieInfo);
    }

    @Then("The film is successfully created with the information provided and with an identity.")
    public void movieIsSave() {
        Assertions.assertNotNull(movieInfoSave);
        Assertions.assertNotNull(movieInfoSave.getId());
        Assertions.assertEquals(movieInfoSave.getName(), movieInfo.getName());
        Assertions.assertEquals(movieInfoSave.getDescription(), movieInfo.getDescription());
        Assertions.assertEquals(movieInfoSave.getPath(), movieInfo.getPath());
    }
}

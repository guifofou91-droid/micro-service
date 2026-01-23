package com.welovecode.moviecatalogservice.glue;

import com.welovecode.moviecatalogservice.model.MovieInfo;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.DataTableType;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.test.web.servlet.client.assertj.RestTestClientResponse;

import java.util.List;
import java.util.Map;

public class CreateMovieStepdefs {

    private final String URL = "/movie-infos/save";

    private RestTestClient restTestClient;

    private MovieInfo movieInfo;

    private RestTestClientResponse response;

    @BeforeStep
    public void setup() {
        restTestClient = RestTestClient.bindToServer()
                .baseUrl("http://localhost:8090")
                .build();
    }

    @DataTableType
    public MovieInfo assetEntry(Map<String, String> entry) {
        return new MovieInfo(entry.get("name"), entry.get("description"), entry.get("path"));
    }

    @Given("I send the following information:")
    public void iSendTheFollowingInformation(List<MovieInfo> movieInfoList) {
        movieInfo = MovieInfo.builder()
                .name(movieInfoList.getFirst().getName())
                .description(movieInfoList.getFirst().getDescription())
                .path(movieInfoList.getFirst().getPath())
                .build();
    }

    @When("I save a movie with those information")
    public void iSaveAMovieWithThoseInformation() {
        response = RestTestClientResponse.from (restTestClient.post().uri(URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .body(movieInfo)
                        .exchange());
    }

    @Then("The movie should be saved successfully")
    public void theMovieShouldBeSavedSuccessfully() {
       Assertions.assertNotNull(response.getExchangeResult().getResponseBodyContent());
    }

    @And("the status is {int}")
    public void theStatusIs(int code) {
        response.getExchangeResult().getStatus().is2xxSuccessful();
    }

    @When("I save a movie with the information {string} and {string}")
    public void iSaveAMovieWithTheInformationAnd(String name, String description) {
        response = RestTestClientResponse.from (restTestClient.post().uri(URL)
                .accept(MediaType.APPLICATION_JSON)
                .body(new MovieInfo(name, description, null))
                .exchange());
    }

    @Then("The movie is expected to fail")
    public void theMovieIsExpectedToFail() {
        response.getExchangeResult().getStatus().is4xxClientError();
    }
}

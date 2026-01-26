package com.welovecode.moviecatalogservice;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = MovieCatalogServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class MovieCatalogServiceApplicationTests {

    @Test
    void contextLoads() {
    }
}

package com.welovecode.moviecatalogservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class MovieInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String path;

    public String getPath() {
        return path;
    }

    public MovieInfo(String name, String description, String path) {
        this.name = name;
        this.description = description;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

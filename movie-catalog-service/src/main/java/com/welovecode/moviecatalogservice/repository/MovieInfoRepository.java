package com.welovecode.moviecatalogservice.repository;

import com.welovecode.moviecatalogservice.model.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MovieInfoRepository extends JpaRepository<MovieInfo, UUID> {
}

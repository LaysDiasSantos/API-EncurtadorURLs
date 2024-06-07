package com.desafio.encurtadorurl.repository;

import com.desafio.encurtadorurl.model.UrlModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlModel, Long> {
    Optional<UrlModel> findByUrlEncurtada(String urlEncurtada);
    Optional<UrlModel> findByUrlOriginal(String urlOriginal);
}
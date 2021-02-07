package com.kshired.url.urlshortener.repository;

import com.kshired.url.urlshortener.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url,Long> {
    List<Url> findByUrl(String url);
    Optional<Url> findByConverted(String converted);
}

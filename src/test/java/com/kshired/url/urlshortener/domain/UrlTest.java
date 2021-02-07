package com.kshired.url.urlshortener.domain;

import com.kshired.url.urlshortener.repository.UrlRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
public class UrlTest {

    @Autowired
    UrlRepository urlRepository;

    @Test
    public void urlTest() {
        // given
        Url url1 = new Url("http://test1.com");

        // when
        urlRepository.save(url1);
        List<Url> all = urlRepository.findByUrl("http://test1.com");

        // then
        assertThat(all.get(0).getUrl()).isEqualTo(url1.getUrl());
    }
}
package com.kshired.url.urlshortener.domain;

import com.kshired.url.urlshortener.repository.UrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;


@SpringBootTest
@Transactional
@Rollback(false)
public class UrlTest {
    @Autowired
    EntityManager em;

    @Autowired
    UrlRepository urlRepository;

    @Test
    public void urlTest() {
        Url url1 = new Url("http://test1.com");
        Url url2 = new Url("http://test2.com");
        em.persist(url1);
        em.persist(url2);

        em.flush();
        em.clear();

        List<Url> all = urlRepository.findAll();
        for (Url url : all) {
            System.out.println("url.getId() = " + url.getId());
        }
    }
}
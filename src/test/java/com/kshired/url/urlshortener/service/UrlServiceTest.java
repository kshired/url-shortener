package com.kshired.url.urlshortener.service;

import com.kshired.url.urlshortener.convert.Base62;
import com.kshired.url.urlshortener.domain.Url;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UrlServiceTest {
    @Autowired
    UrlService urlService;
    private static final String testUrl = "http://naver.com";

    @Test
    public void saveUrlTest() {
        // given
        Url url = new Url(testUrl);

        // when
        Url savedUrl = urlService.save(url);

        // then
        Base62 converter = new Base62();
        assertThat(converter.urlDecoder(url.getConverted())).isEqualTo(savedUrl.getId());
    }


    @Test
    public void existUrlTest() {
        // given
        urlService.save(new Url(testUrl));

        // when
        Boolean result = urlService.existUrl(testUrl);

        // then
        assertThat(result).isTrue();
    }

    @Test
    public void convertedUrlTest() {
        // given
        Url url = new Url(testUrl);
        Url savedUrl = urlService.save(url);

        // when
        Url findUrl = urlService.findByConverted(savedUrl.getConverted());

        // then
        assertThat(findUrl.getUrl()).isEqualTo(testUrl);
    }
}
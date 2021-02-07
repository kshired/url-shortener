package com.kshired.url.urlshortener.service;

import com.kshired.url.urlshortener.convert.Base62;
import com.kshired.url.urlshortener.domain.Url;
import com.kshired.url.urlshortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UrlService {
    private final UrlRepository urlRepository;

    @Transactional
    public Url save(Url url) {
        urlRepository.save(url);

        Base62 base62 = new Base62();
        String encodedUrl = base62.urlEncoder(url.getId().toString());
        url.changeConvertedUrl(encodedUrl);

        return url;
    }

    public Boolean existUrl(String url){
        return !urlRepository.findByUrl(url).isEmpty();
    }

    public String findConvertedUrlByOrigin(String origin) {
        return urlRepository.findByUrl(origin).get(0).getConverted();
    }

    public Url findByConverted(String converted) {
        Optional<Url> findUrl = urlRepository.findByConverted(converted);
        return findUrl.orElse(null);
    }

}

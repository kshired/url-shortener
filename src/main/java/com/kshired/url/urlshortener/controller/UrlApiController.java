package com.kshired.url.urlshortener.controller;

import com.kshired.url.urlshortener.domain.Url;
import com.kshired.url.urlshortener.service.UrlService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
public class UrlApiController {
    private final UrlService urlService;
    private static final String URL_PREFIX = "http://localhost:8080/short/";

    @PostMapping("/short-url")
    public CreateUrlResponse saveUrl(@RequestBody @Valid CreateUrlRequest request ) {
        if(urlService.existUrl(request.originUrl)){
            return new CreateUrlResponse(urlService.findConvertedUrlByOrigin(request.originUrl));
        }
        Url savedUrl = urlService.save(new Url(request.getOriginUrl()));
        return new CreateUrlResponse(savedUrl.getConverted());
    }

    @GetMapping("/short/{convertedUrl}")
    public ModelAndView redirectOriginUrl(@PathVariable("convertedUrl") String converted) {
        Url origin = urlService.findByConverted(URL_PREFIX+converted);
        return new ModelAndView("redirect:"+origin.getUrl());
    }

    @Data
    static class CreateUrlRequest {
        @NotNull
        private String originUrl;
    }

    @Data
    @AllArgsConstructor
    static class CreateUrlResponse {
        @NotNull
        private String convertedUrl;
    }
}

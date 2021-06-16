package test.lowes.urlShortner.lowesurlShortner.controller;


import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.lowes.urlShortner.lowesurlShortner.model.vo.ShortenRequest;
import test.lowes.urlShortner.lowesurlShortner.service.URLConverterService;

import java.net.URI;

@RestController
public class EntryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntryController.class);
    private final URLConverterService urlService;

    public EntryController(URLConverterService urlService) {
        this.urlService = urlService;
    }

    @ApiOperation(value = "Convert new url", notes = "Converts long url to short url")
    @PostMapping("create-short")
    public String convertToShortUrl(@RequestBody ShortenRequest request) {
        return urlService.convertToShortUrl(request);
    }

    @ApiOperation(value = "Redirect", notes = "Finds original url from short url and redirects")
    @GetMapping(value = "{shortUrl}")
    @Cacheable(value = "urls", key = "#shortUrl", sync = true)
    public ResponseEntity<Void> getAndRedirect(@PathVariable String shortUrl) {
        var url = urlService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}



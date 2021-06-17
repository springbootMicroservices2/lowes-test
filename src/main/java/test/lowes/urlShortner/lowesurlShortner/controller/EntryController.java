package test.lowes.urlShortner.lowesurlShortner.controller;


import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.lowes.urlShortner.lowesurlShortner.model.entity.Url;
import test.lowes.urlShortner.lowesurlShortner.model.vo.ShortenRequest;
import test.lowes.urlShortner.lowesurlShortner.service.URLConverterService;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EntryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntryController.class);
    private static final String GET_BY_ID_RETURN ="Returning Long Url {}" ;
    private final URLConverterService urlService;

    public EntryController(URLConverterService urlService) {
        this.urlService = urlService;
    }

    @ApiOperation(value = "Convert new url", notes = "Converts long url to short url")
    @PostMapping("create-short")
    public String convertToShortUrl(@RequestBody ShortenRequest request) throws MalformedURLException {
        return urlService.convertToShortUrl(request);
    }

    @ApiOperation(value = "Redirect", notes = "Finds original url from short url and redirects")
    @Cacheable(value = "urls", key = "#shortUrl", sync = true)
    @GetMapping(path = "/getById")
    public ResponseEntity<String> getById(String id) {
        String url = urlService.getOriginalUrl(id);

        if (url != null ){
            return new ResponseEntity<>(
                    url ,
                    HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }
}



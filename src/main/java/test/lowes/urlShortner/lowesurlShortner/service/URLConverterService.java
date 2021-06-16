package test.lowes.urlShortner.lowesurlShortner.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import test.lowes.urlShortner.lowesurlShortner.model.entity.Url;
import test.lowes.urlShortner.lowesurlShortner.model.vo.ShortenRequest;
import test.lowes.urlShortner.lowesurlShortner.repository.URLRepository;
import test.lowes.urlShortner.lowesurlShortner.util.BaseConverter;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class URLConverterService {
    private static final Logger LOGGER = LoggerFactory.getLogger(URLConverterService.class);
    private final URLRepository urlRepository;
    private final BaseConverter conversion;

    public URLConverterService(URLRepository urlRepository, BaseConverter baseConversion) {
        this.urlRepository = urlRepository;
        this.conversion = baseConversion;
    }

    public String convertToShortUrl(ShortenRequest request) {
        var url = new Url();
        url.setLongUrl(request.getLongUrl());
        url.setExpiresDate(request.getExpiresDate());
        url.setCreatedDate(new Date());
        var entity = urlRepository.save(url);

        return conversion.encode(entity.getId());
    }

    public String getOriginalUrl(String shortUrl) {
        var id = conversion.decode(shortUrl);
        var entity = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));

        if (entity.getExpiresDate() != null && entity.getExpiresDate().before(new Date())){
            urlRepository.delete(entity);
            throw new EntityNotFoundException("Link expired!");
        }

        return entity.getLongUrl();
    }

}

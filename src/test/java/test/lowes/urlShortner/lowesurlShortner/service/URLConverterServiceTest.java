package test.lowes.urlShortner.lowesurlShortner.service;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import test.lowes.urlShortner.lowesurlShortner.model.entity.Url;
import test.lowes.urlShortner.lowesurlShortner.model.vo.ShortenRequest;
import test.lowes.urlShortner.lowesurlShortner.repository.URLRepository;
import test.lowes.urlShortner.lowesurlShortner.util.BaseConverter;

import java.net.MalformedURLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class URLConverterServiceTest {

    @Mock
    URLRepository mockUrlRepository;

    @Mock
    BaseConverter mockBaseConversion;

    @InjectMocks
    URLConverterService urlService;

    @Test
    public void convertToShortUrlTest() throws MalformedURLException {
        var url = new Url();
        url.setLongUrl("https://github.com/AnteMarin/UrlShortener-API");
        url.setCreatedDate(new Date());
        url.setId(5);

        when(mockUrlRepository.save(any(Url.class))).thenReturn(url);
        when(mockBaseConversion.encode(url.getId())).thenReturn("f");

        var urlRequest = new ShortenRequest();
        urlRequest.setLongUrl("https://github.com");

        assertEquals("f", urlService.convertToShortUrl(urlRequest));
    }

    @Test
    public void getOriginalUrlTest() {
        when(mockBaseConversion.decode("h")).thenReturn((long) 7);

        var url = new Url();
        url.setLongUrl("https://github.com");
        url.setCreatedDate(new Date());
        url.setId(7);

        when(mockUrlRepository.findById((long) 7)).thenReturn(java.util.Optional.of(url));
        assertEquals("https://github.com", urlService.getOriginalUrl("h"));

    }
}
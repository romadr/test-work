package ru.roman.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.roman.dto.SearchResponseDto;
import ru.roman.dto.stack.exchange.StackExchangeResponseDto;
import ru.roman.mappers.SearchMapper;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

@Service
public class StackExchangeService {

    private static final Cache<Object, Object> CACHE_5MIN;
    private static final String ACCEPT_ENCODING = "Accept-Encoding";
    private static final String GZIP = "gzip";
    private static final String TAG = "StackExchangeService";

    static {
        CACHE_5MIN = CacheBuilder.newBuilder()
                .maximumSize(15000)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build();
    }

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SearchMapper searchMapper;

    @Value("${stack.exchange.url}")
    private String baseUrlPattern;

    private String createRequestUrlString(final String searchString) {
        return String.format(baseUrlPattern, searchString);
    }

    public SearchResponseDto search(final String searchString) {
        final String cacheKey = String.format("StackExchangeService$search$%s", searchString);

        try {
            final Object cached = CACHE_5MIN.get(cacheKey, (Callable<SearchResponseDto>) () -> {
                final String url = createRequestUrlString(searchString);
                final StackExchangeResponseDto stackExchangeResponseDto = requestEntity(url, StackExchangeResponseDto.class);
                return searchMapper.fromStackExchangeResponse(stackExchangeResponseDto);
            });
            return (SearchResponseDto) cached;
        } catch (Exception ex) {
            log.error(TAG, ex);
            throw new InternalError(ex);
        }
    }

    private <T> T requestEntity(final String url, Class<T> tClass) throws Exception {
        final HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestProperty(ACCEPT_ENCODING, GZIP);

        final Reader reader;
        if (GZIP.equals(con.getContentEncoding())) {
            reader = new InputStreamReader(new GZIPInputStream(con.getInputStream()));
        } else {
            reader = new InputStreamReader(con.getInputStream());
        }
        return objectMapper.readValue(reader, tClass);
    }


}

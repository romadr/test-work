package ru.roman.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.roman.dto.StackExchangeResponseDto;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

@Service
public class StackExchangeService {

    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String GZIP = "gzip";

    @Autowired
    ObjectMapper objectMapper;


    public StackExchangeResponseDto search() throws Exception {
        final String url = "http://api.stackexchange.com/2.2/search?order=desc&sort=activity&intitle=java&site=stackoverflow";
        return requestEntity(url, StackExchangeResponseDto.class);
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

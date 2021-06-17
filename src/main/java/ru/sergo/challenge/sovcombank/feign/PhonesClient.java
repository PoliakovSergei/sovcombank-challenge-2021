package ru.sergo.challenge.sovcombank.feign;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.http.MediaType;
import ru.sergo.challenge.sovcombank.dto.PhonesResponse;

public interface PhonesClient {
    @RequestLine("GET /api/v1/phones/{id}")
    @Headers("Content-Type: " + MediaType.APPLICATION_JSON_VALUE)
    PhonesResponse getUserPhonesById(@Param("id") Integer id);
}

package ru.sergo.challenge.sovcombank.config;

import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sergo.challenge.sovcombank.feign.PhonesClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignConfig {

    @Value("${service.phones.url}")
    private String phonesUrl;

    @Bean(name = "phonesClient")
    public PhonesClient phonesClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .retryer(new Retryer.Default(0, 0, 0))
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logLevel(Logger.Level.BASIC)
                .options(new Request.Options(5, TimeUnit.SECONDS, 5, TimeUnit.SECONDS, true))
                .target(PhonesClient.class, phonesUrl);
    }

}

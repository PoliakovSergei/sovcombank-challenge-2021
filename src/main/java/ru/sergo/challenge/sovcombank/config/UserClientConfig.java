package ru.sergo.challenge.sovcombank.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.http.ClientHttpRequestMessageSender;
import ru.sergo.challenge.sovcombank.soap.UserClient;

@Configuration
public class UserClientConfig {

    private static final int CONNECTION_TIMEOUT = (5 * 1000);
    private static final int READ_TIMEOUT = (5 * 1000);

    @Value("${service.users.url}")
    private String userUrl;


    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ru.sergo.challenge.sovcombank.generated");
        return marshaller;
    }

    @Bean
    public UserClient userClient(Jaxb2Marshaller marshaller) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(CONNECTION_TIMEOUT);
        requestFactory.setReadTimeout(READ_TIMEOUT);
        UserClient client = new UserClient();
        client.setDefaultUri(userUrl);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        client.setMessageSender(new ClientHttpRequestMessageSender(requestFactory));
        return client;
    }

}

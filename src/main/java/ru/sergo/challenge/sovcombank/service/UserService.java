package ru.sergo.challenge.sovcombank.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.WebServiceIOException;
import ru.sergo.challenge.sovcombank.dto.PhonesResponse;
import ru.sergo.challenge.sovcombank.dto.UserResponse;
import ru.sergo.challenge.sovcombank.enums.ResponseCode;
import ru.sergo.challenge.sovcombank.feign.PhonesClient;
import ru.sergo.challenge.sovcombank.generated.User;
import ru.sergo.challenge.sovcombank.soap.UserClient;
import ru.sergo.challenge.sovcombank.util.FutureUtil;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;
    private final PhonesClient phonesClient;

    public UserResponse getUser(Integer id) {
        try {
            CompletableFuture<String> usernameFuture = CompletableFuture.supplyAsync(() -> getUsername(id));
            CompletableFuture<String> mainPhoneFuture = CompletableFuture.supplyAsync(() -> getMainPhone(id));
            String username = usernameFuture.get();
            String mainPhone = FutureUtil.getFromFutureOrNull(mainPhoneFuture);
            return new UserResponse(ResponseCode.SUCCESS.getCode(), username, mainPhone);
        } catch (Exception e) {
            if (e.getCause() != null && e.getCause() instanceof WebServiceIOException) {
                return new UserResponse(ResponseCode.TIMEOUT.getCode());
            }
            return new UserResponse(ResponseCode.ERROR.getCode());
        }
    }

    private String getUsername(Integer id) {
        User userInfo = userClient.getUser(id).getUser();
        return userInfo.getFirstName()
                + " "
                + userInfo.getLastName();
    }

    private String getMainPhone(Integer id) {
        try {
            PhonesResponse phonesResponse = phonesClient.getUserPhonesById(id);
            return phonesResponse.getPhones().get(0);
        } catch (Exception e) {
            log.error("Не удалось получить номер телефона для пользователя с id = {}", id);
            return null;
        }
    }
}

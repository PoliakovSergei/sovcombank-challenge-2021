package ru.sergo.challenge.sovcombank.soap;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import ru.sergo.challenge.sovcombank.generated.GetUserRequest;
import ru.sergo.challenge.sovcombank.generated.GetUserResponse;

public class UserClient extends WebServiceGatewaySupport {
    public GetUserResponse getUser(Integer id) {
        GetUserRequest request = new GetUserRequest();
        request.setUserId(id);
        return (GetUserResponse) getWebServiceTemplate().marshalSendAndReceive(request);
    }
}

package com.server.Routes;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

import java.util.LinkedList;

public class EatCookie implements BaseController {
    private final LinkedList<String> memory;

    public EatCookie(LinkedList<String> memory) {
        this.memory = memory;
    }

    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
           HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        httpResponse.statusCode(HttpStatusCode.OK);
        String clientCookie = clientHttpRequest.getInformation("Cookie").split(":")[1];
        if (clientCookie.equals(memory.getLast())) {
            httpResponse.content("mmmm chocolate");
        }

        return httpResponse;
    }
}

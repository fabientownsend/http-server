package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

import java.util.LinkedList;

public class EatCookie implements BaseController {
    private final HttpResponse httpResponse;
    private final LinkedList<String> memory;
    private final ClientHttpRequest clientHttpRequest;

    public EatCookie(ClientHttpRequest clientHttpRequest, LinkedList<String> memory) {
        this.httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        this.memory = memory;
        this.clientHttpRequest = clientHttpRequest;
    }

    public HttpResponse execute() {
        httpResponse.statusCode(200);
        String clientCookie = clientHttpRequest.getInformation("Cookie").split(":")[1];
        if (clientCookie.equals(memory.getLast())) {
            httpResponse.content("mmmm chocolate");
        }

        return httpResponse;
    }
}

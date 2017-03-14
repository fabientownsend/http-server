package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;

import java.util.LinkedList;

public class EatCookie implements BaseController {
    private final HttpServerResponse httpServerResponse;
    private final LinkedList<String> memory;
    private final ClientHttpRequest clientHttpRequest;

    public EatCookie(ClientHttpRequest clientHttpRequest, LinkedList<String> memory) {
        this.httpServerResponse = new HttpServerResponse(clientHttpRequest.getHttpVersion());
        this.memory = memory;
        this.clientHttpRequest = clientHttpRequest;
    }

    public HttpServerResponse execute() {
        httpServerResponse.setHttpResponseCode(200);
        String clientCookie = clientHttpRequest.getInformation("Cookie").split(":")[1];
        if (clientCookie.equals(memory.getLast())) {
            httpServerResponse.setBody("mmmm chocolate");
        }

        return httpServerResponse;
    }
}

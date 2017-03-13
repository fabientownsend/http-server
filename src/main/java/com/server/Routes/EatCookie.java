package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;

import java.util.LinkedList;

public class EatCookie implements BaseController {
    private final HttpServerResponse httpServerResponse;
    private final LinkedList<String> memory;

    public EatCookie(HttpServerResponse httpServerResponse, ClientHttpRequest clientHttpRequest, LinkedList<String> memory) {
        this.httpServerResponse = httpServerResponse;
        this.memory = memory;
    }

    public HttpServerResponse execute() {
        httpServerResponse.setHttpResponseCode(200);
        httpServerResponse.setBody("mmmm" + memory.get(0));
        return httpServerResponse;
    }
}

package com.server.Cookie;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;
import com.server.Routes.BaseController;

import java.util.LinkedList;

public class Cookie implements BaseController {

    private final HttpServerResponse httpServerResponse;
    private final ClientHttpRequest clientHttpRequest;
    private final LinkedList<String> memory;

    public Cookie(HttpServerResponse httpServerResponse, ClientHttpRequest clientHttpRequest, LinkedList<String> memory) {
        this.clientHttpRequest = clientHttpRequest;
        this.httpServerResponse = httpServerResponse;
        this.memory = memory;
    }

    public HttpServerResponse execute() {
        String parameters = extractParameters(clientHttpRequest.getUri());
        String cookie = parameters.split("=")[1];
        memory.add(cookie);

        httpServerResponse.setHttpResponseCode(200);
        httpServerResponse.setHeader("Set-Cookie", "type:chocolate");
        httpServerResponse.setBody(
                "Eat\r\n");
        return httpServerResponse;
    }

    private String extractParameters(String uri) {
        if (uri.contains("?")) {
            return uri.split("\\?")[1];
        } else {
            return "";
        }
    }
}

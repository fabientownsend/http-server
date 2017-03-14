package com.server.Cookie;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.Routes.BaseController;

import java.util.LinkedList;

public class Cookie implements BaseController {

    private final HttpResponse httpResponse;
    private final ClientHttpRequest clientHttpRequest;
    private final LinkedList<String> memory;

    public Cookie(ClientHttpRequest clientHttpRequest, LinkedList<String> memory) {
        this.httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        this.clientHttpRequest = clientHttpRequest;
        this.memory = memory;
    }

    public HttpResponse execute() {
        String parameters = extractParameters(clientHttpRequest.getUri());
        String cookie = parameters.split("=")[1];
        memory.add(cookie);

        httpResponse.statusCode(200);
        httpResponse.addHeader("Set-Cookie", "type:chocolate");
        httpResponse.content(
                "Eat\r\n");
        return httpResponse;
    }

    private String extractParameters(String uri) {
        if (uri.contains("?")) {
            return uri.split("\\?")[1];
        } else {
            return "";
        }
    }
}

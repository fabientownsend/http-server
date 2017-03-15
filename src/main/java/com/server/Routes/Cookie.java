package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

import java.util.LinkedList;

public class Cookie implements BaseController {
    private final LinkedList<String> memory;

    public Cookie(LinkedList<String> memory) {
        this.memory = memory;
    }

    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
       HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        String query = firstQuery(clientHttpRequest.getUri());
        String cookieValue = query.split("=")[1];
        memory.add(cookieValue);

        httpResponse.statusCode(200);
        httpResponse.addHeader("Set-Cookie", "type:chocolate");
        httpResponse.content("Eat\r\n");
        return httpResponse;
    }

    private String firstQuery(String uri) {
        if (uri.contains("?")) {
            return uri.split("\\?")[1];
        } else {
            return "";
        }
    }
}

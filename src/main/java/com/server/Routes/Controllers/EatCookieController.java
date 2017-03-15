package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

import java.util.LinkedList;

public class EatCookieController implements BaseController {
    private final LinkedList<String> memory;

    public EatCookieController(LinkedList<String> memory) {
        this.memory = memory;
    }

    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        httpResponse.statusCode(HttpStatusCode.OK);

        String clientCookie = clientHttpRequest.getInformation("Cookie");
        System.out.println("client cookie: " + clientCookie);
        if (clientCookie.equals(memory.getLast())) {
            httpResponse.content("mmmm chocolate");
        }

        return httpResponse;
    }
}

package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpHeaders;
import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.Routes.Memory;

public class EatCookieController implements BaseController {
    private Memory memory;

    public EatCookieController(Memory memory) {
        this.memory = memory;
    }

    public HttpResponse doGet(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse();

        httpResponse.statusCode(HttpStatusCode.OK);

        if (clientCookie(clientHttpRequest).equals(memory.content())) {
            httpResponse.content("mmmm chocolate");
        }

        return httpResponse;
    }

    private String clientCookie(ClientHttpRequest clientHttpRequest) {
        return clientHttpRequest.getInformation(HttpHeaders.COOKIE);
    }
}

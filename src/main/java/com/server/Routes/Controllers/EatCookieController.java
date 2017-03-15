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

    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        httpResponse.statusCode(HttpStatusCode.OK);

        String clientCookie = clientHttpRequest.getInformation(HttpHeaders.COOKIE);

        if (clientCookie.equals(memory.content())) {
            httpResponse.content("mmmm chocolate");
        }

        return httpResponse;
    }
}

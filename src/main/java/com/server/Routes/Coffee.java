package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

public class Coffee implements BaseController {

    private final HttpResponse httpResponse;

    public Coffee(ClientHttpRequest clientHttpRequest) {
        this.httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
    }

    public HttpResponse execute() {
        httpResponse.statusCode(418);
        httpResponse.addHeader("Content-Type", "text/html");
        httpResponse.content("I'm a teapot");
        return httpResponse;
    }
}

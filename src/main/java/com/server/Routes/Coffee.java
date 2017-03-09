package com.server.Routes;

import com.server.HttpResponse.HttpServerResponse;

public class Coffee implements BaseController {

    private final HttpServerResponse httpServerResponse;

    public Coffee(HttpServerResponse httpServerResponse) {
        this.httpServerResponse = httpServerResponse;
    }

    public HttpServerResponse execute() {
        httpServerResponse.setHttpResponseCode(418);
        httpServerResponse.setHeader("Content-Type", "text/html");
        httpServerResponse.setBody("I'm a teapot");
        return httpServerResponse;
    }
}

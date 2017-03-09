package com.server.Routes;

import com.server.HttpResponse.HttpServerResponse;

public class MethodOptions2 implements BaseController {
    private final HttpServerResponse httpServerResponse;

    public MethodOptions2(HttpServerResponse httpServerResponse) {
        this.httpServerResponse = httpServerResponse;
    }

    public HttpServerResponse execute() {
        httpServerResponse.setHttpResponseCode(200);
        httpServerResponse.setHeader("Allow", "GET,OPTIONS");
        return httpServerResponse;
    }
}

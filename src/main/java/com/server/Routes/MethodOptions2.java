package com.server.Routes;

import com.server.HttpResponse.HttpServerResponse;

public class MethodOptions2 implements UpstreamService {
    private final HttpServerResponse httpServerResponse;

    public MethodOptions2(HttpServerResponse httpServerResponse) {
        this.httpServerResponse = httpServerResponse;
    }

    public HttpServerResponse generateContent() {
        httpServerResponse.setHttpResponseCode(200);
        httpServerResponse.setHeader("Allow", "GET,OPTIONS");
        return httpServerResponse;
    }
}

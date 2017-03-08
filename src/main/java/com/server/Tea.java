package com.server;

public class Tea implements UpstreamService {
    private final HttpServerResponse httpServerResponse;

    public Tea(HttpServerResponse httpServerResponse) {
        this.httpServerResponse = httpServerResponse;
    }

    public HttpServerResponse generateContent() {
        httpServerResponse.setHttpResponseCode(200);
        httpServerResponse.setHeader("Content-Type", "text/html");
        return httpServerResponse;
    }
}

package com.server;

public class DefaultPage implements UpstreamService {
    private final HttpServerResponse httpServerResponse;

    public DefaultPage(HttpServerResponse httpServerResponse) {
        this.httpServerResponse = httpServerResponse;
    }

    public HttpServerResponse generateContent() {
        httpServerResponse.setHttpResponseCode(200);
        return  httpServerResponse;
    }
}

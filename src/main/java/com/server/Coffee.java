package com.server;

public class Coffee implements UpstreamService {

    private final HttpServerResponse httpServerResponse;

    public Coffee(HttpServerResponse httpServerResponse) {
        this.httpServerResponse = httpServerResponse;
    }

    public HttpServerResponse generateContent() {
        httpServerResponse.setHttpResponseCode(418);
        httpServerResponse.setHeader("Content-Type", "text/html");
        httpServerResponse.setBody("I'm a teapot");
        return httpServerResponse;
    }
}

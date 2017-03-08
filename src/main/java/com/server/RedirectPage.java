package com.server;

public class RedirectPage implements UpstreamService {
    private final HttpServerResponse httpServerResponse;

    public RedirectPage(HttpServerResponse httpServerResponse) {
        this.httpServerResponse = httpServerResponse;
    }

    public HttpServerResponse generateContent() {
        httpServerResponse.setHttpResponseCode(302);
        httpServerResponse.setHeader("Location", "http://localhost:5000/");
        return httpServerResponse;
    }
}

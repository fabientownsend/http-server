package com.server.Routes;

import com.server.HttpResponse.HttpServerResponse;

public class NotFoundPage implements UpstreamService {
    private final HttpServerResponse httpServerResponse;

    public NotFoundPage(HttpServerResponse httpServerResponse) {
        this.httpServerResponse = httpServerResponse;
    }

    public HttpServerResponse generateContent() {
        httpServerResponse.setHttpResponseCode(404);
        return httpServerResponse;
    }
}

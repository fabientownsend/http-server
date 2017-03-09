package com.server.Routes;

import com.server.HttpResponse.HttpServerResponse;

public class RedirectPage implements UpstreamService {
    private final HttpServerResponse httpServerResponse;

    public RedirectPage(HttpServerResponse httpServerResponse) {
        this.httpServerResponse = httpServerResponse;
    }

    public HttpServerResponse execute() {
        httpServerResponse.setHttpResponseCode(302);
        httpServerResponse.setHeader("Location", "http://localhost:5000/");
        return httpServerResponse;
    }
}

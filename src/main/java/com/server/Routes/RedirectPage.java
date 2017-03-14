package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;

public class RedirectPage implements BaseController {
    private final HttpServerResponse httpServerResponse;

    public RedirectPage(ClientHttpRequest clientHttpRequest) {
        this.httpServerResponse = new HttpServerResponse(clientHttpRequest.getHttpVersion());
    }

    public HttpServerResponse execute() {
        httpServerResponse.setHttpResponseCode(302);
        httpServerResponse.setHeader("Location", "http://localhost:5000/");
        return httpServerResponse;
    }
}

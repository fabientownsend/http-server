package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;

public class NotFoundPage implements BaseController {
    private final HttpServerResponse httpServerResponse;

    public NotFoundPage(ClientHttpRequest clientHttpRequest) {
        this.httpServerResponse = new HttpServerResponse(clientHttpRequest.getHttpVersion());
    }

    public HttpServerResponse execute() {
        httpServerResponse.setHttpResponseCode(404);
        return httpServerResponse;
    }
}

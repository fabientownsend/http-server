package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;
import com.server.HttpVerb;

public class MethodOptions implements BaseController {
    private final ClientHttpRequest clientHttpRequest;
    private final HttpServerResponse httpServerResponse;

    public MethodOptions(ClientHttpRequest clientHttpRequest) {
        this.httpServerResponse = new HttpServerResponse(clientHttpRequest.getHttpVersion());
        this.clientHttpRequest = clientHttpRequest;
    }

    public HttpServerResponse execute() {
        httpServerResponse.setHttpResponseCode(200);

        if (clientHttpRequest.getVerb().equals(HttpVerb.OPTIONS.name())) {
            httpServerResponse.setHeader("Allow", "GET,HEAD,POST,OPTIONS,PUT");
        } else {
            httpServerResponse.setHeader("Allow", "GET,OPTIONS");
        }

        return httpServerResponse;
    }
}

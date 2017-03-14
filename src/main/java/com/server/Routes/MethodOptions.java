package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.HttpVerb;

public class MethodOptions implements BaseController {
    private final ClientHttpRequest clientHttpRequest;
    private final HttpResponse httpResponse;

    public MethodOptions(ClientHttpRequest clientHttpRequest) {
        this.httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        this.clientHttpRequest = clientHttpRequest;
    }

    public HttpResponse execute() {
        httpResponse.statusCode(200);

        if (clientHttpRequest.getVerb().equals(HttpVerb.OPTIONS.name())) {
            httpResponse.addHeader("Allow", "GET,HEAD,POST,OPTIONS,PUT");
        } else {
            httpResponse.addHeader("Allow", "GET,OPTIONS");
        }

        return httpResponse;
    }
}

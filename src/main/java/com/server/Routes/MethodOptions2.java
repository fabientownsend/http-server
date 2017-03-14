package com.server.Routes;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

public class MethodOptions2 implements BaseController {
    private final HttpResponse httpResponse;

    public MethodOptions2(ClientHttpRequest clientHttpRequest) {
        this.httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
    }

    public HttpResponse execute() {
        httpResponse.statusCode(HttpStatusCode.OK);
        httpResponse.addHeader("Allow", "GET,OPTIONS");
        return httpResponse;
    }
}

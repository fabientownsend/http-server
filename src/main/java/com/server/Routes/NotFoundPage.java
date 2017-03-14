package com.server.Routes;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

public class NotFoundPage implements BaseController {
    private final HttpResponse httpResponse;

    public NotFoundPage(ClientHttpRequest clientHttpRequest) {
        this.httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
    }

    public HttpResponse execute() {
        httpResponse.statusCode(HttpStatusCode.NOT_FOUND);
        return httpResponse;
    }
}

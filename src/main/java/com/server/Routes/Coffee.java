package com.server.Routes;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

public class Coffee implements BaseController {

    private final HttpResponse httpResponse;

    public Coffee(ClientHttpRequest clientHttpRequest) {
        this.httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
    }

    public HttpResponse execute() {
        httpResponse.statusCode(HttpStatusCode.I_M_A_TEAPOT);
        httpResponse.addHeader("Content-Type", "text/html");
        httpResponse.content("I'm a teapot");
        return httpResponse;
    }
}

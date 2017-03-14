package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

public class RedirectPage implements BaseController {
    private final HttpResponse httpResponse;

    public RedirectPage(ClientHttpRequest clientHttpRequest) {
        this.httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
    }

    public HttpResponse execute() {
        httpResponse.statusCode(302);
        httpResponse.addHeader("Location", "http://localhost:5000/");
        return httpResponse;
    }
}

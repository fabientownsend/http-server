package com.server.Routes;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpResponse.HttpResponse;

public class Tea implements BaseController {
    private final HttpResponse httpResponse;

    public Tea(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public HttpResponse execute() {
        httpResponse.statusCode(HttpStatusCode.OK);
        httpResponse.addHeader("Content-Type", "text/html");
        return httpResponse;
    }
}

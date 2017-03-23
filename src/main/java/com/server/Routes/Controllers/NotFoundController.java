package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

public class NotFoundController implements BaseController {
    public HttpResponse doHead(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.statusCode(HttpStatusCode.NOT_FOUND);
        return httpResponse;
    }

    public HttpResponse doGet(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.statusCode(HttpStatusCode.NOT_FOUND);
        return httpResponse;
    }
}

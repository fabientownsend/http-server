package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

public class MethodOptions2Controller implements BaseController {
    public HttpResponse doGet(ClientHttpRequest clientHttpRequest) {
        return defaultResponse();
    }

    public HttpResponse doPost(ClientHttpRequest clientHttpRequest) {
        return defaultResponse();
    }

    public HttpResponse doPut(ClientHttpRequest clientHttpRequest) {
        return defaultResponse();
    }

    public HttpResponse doHead(ClientHttpRequest clientHttpRequest) {
        return defaultResponse();
    }

    public HttpResponse doOptions(ClientHttpRequest clientHttpRequest) {
        return defaultResponse();
    }

    public HttpResponse defaultResponse() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.statusCode(HttpStatusCode.OK);

        httpResponse.addHeader("Allow", "GET,OPTIONS");

        return httpResponse;
    }
}

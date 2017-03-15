package com.server.Routes;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

public class MethodOptions2 implements BaseController {
    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        httpResponse.statusCode(HttpStatusCode.OK);
        httpResponse.addHeader("Allow", "GET,OPTIONS");
        return httpResponse;
    }
}

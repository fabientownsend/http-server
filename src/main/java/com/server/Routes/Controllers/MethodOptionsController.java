package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.HttpVerb;

public class MethodOptionsController implements BaseController {
    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        httpResponse.statusCode(HttpStatusCode.OK);

        if (clientHttpRequest.getVerb().equals(HttpVerb.OPTIONS.name())) {
            httpResponse.addHeader("Allow", "GET,HEAD,POST,OPTIONS,PUT");
        } else {
            httpResponse.addHeader("Allow", "GET,OPTIONS");
        }

        return httpResponse;
    }
}

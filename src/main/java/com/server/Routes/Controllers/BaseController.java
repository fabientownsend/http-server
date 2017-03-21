package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

public interface BaseController {
    default HttpResponse execute(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.statusCode(HttpStatusCode.METHOD_NOT_ALLOWED);
        return httpResponse;
    }
}

package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

public class RedirectionController implements BaseController {
    public HttpResponse doGet(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.statusCode(HttpStatusCode.OBJECT_MOVED);
        httpResponse.addHeader("Location", "http://localhost:5000/");
        return httpResponse;
    }
}

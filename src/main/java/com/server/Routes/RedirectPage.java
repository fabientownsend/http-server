package com.server.Routes;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

public class RedirectPage implements BaseController {
    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        httpResponse.statusCode(HttpStatusCode.OBJECT_MOVED);
        httpResponse.addHeader("Location", "http://localhost:5000/");
        return httpResponse;
    }
}

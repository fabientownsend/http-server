package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

public class CoffeeController implements BaseController {
    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        httpResponse.statusCode(HttpStatusCode.I_M_A_TEAPOT);
        httpResponse.addHeader("Content-Type", "text/html");
        httpResponse.content("I'm a teapot");
        return httpResponse;
    }
}

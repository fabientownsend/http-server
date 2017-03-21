package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.HttpVerb;

public class TeaController implements BaseController {
    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());

        if (clientHttpRequest.getVerb() == HttpVerb.GET) {
            httpResponse.statusCode(HttpStatusCode.OK);
        } else {
            httpResponse.statusCode(HttpStatusCode.METHOD_NOT_ALLOWED);
        }

        return httpResponse;
    }
}

package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

public interface BaseController {
    default HttpResponse doGet(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.statusCode(HttpStatusCode.METHOD_NOT_ALLOWED);
        return httpResponse;
    }

    default HttpResponse doPost(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.statusCode(HttpStatusCode.METHOD_NOT_ALLOWED);
        return httpResponse;
    }

    default HttpResponse doPut(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.statusCode(HttpStatusCode.METHOD_NOT_ALLOWED);
        return httpResponse;
    }

    default HttpResponse doDelete(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.statusCode(HttpStatusCode.METHOD_NOT_ALLOWED);
        return httpResponse;
    }

    default HttpResponse  doOptions(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.statusCode(HttpStatusCode.METHOD_NOT_ALLOWED);
        return httpResponse;
    }

    default HttpResponse doPatch(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.statusCode(HttpStatusCode.METHOD_NOT_ALLOWED);
        return httpResponse;
    }

    default HttpResponse doHead(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.statusCode(HttpStatusCode.METHOD_NOT_ALLOWED);
        return httpResponse;
    }
}

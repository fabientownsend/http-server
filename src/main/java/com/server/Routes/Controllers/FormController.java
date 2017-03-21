package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.Routes.Memory;

public class FormController implements BaseController {
    private Memory memory;

    public FormController(Memory memory) {
        this.memory = memory;
    }

    public HttpResponse doGet(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());

        httpResponse.statusCode(HttpStatusCode.OK);

        if (!memory.content().isEmpty()) {
            httpResponse.content(memory.content());
        }

        return httpResponse;
    }

    public HttpResponse doDelete(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());

        httpResponse.statusCode(HttpStatusCode.OK);
        memory.remove();

        return httpResponse;
    }

    public HttpResponse doPost(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());

        httpResponse.statusCode(HttpStatusCode.OK);
        memory.setContent(clientHttpRequest.getBody());

        return httpResponse;
    }

    public HttpResponse doPut(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());

        httpResponse.statusCode(HttpStatusCode.OK);
        memory.setContent(clientHttpRequest.getBody());

        return httpResponse;
    }
}

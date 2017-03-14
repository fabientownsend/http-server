package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;
import com.server.HttpVerb;

import java.util.LinkedList;

public class FormPage implements BaseController {
    private final ClientHttpRequest clientHttpRequest;
    private final HttpServerResponse httpServerResponse;
    private LinkedList<String> memory;

    public FormPage(ClientHttpRequest clientHttpRequest, LinkedList<String> memory) {
        this.httpServerResponse = new HttpServerResponse(clientHttpRequest.getHttpVersion());
        this.clientHttpRequest = clientHttpRequest;
        this.memory = memory;
    }

    public HttpServerResponse execute() {
        httpServerResponse.setHttpResponseCode(200);

        if (clientHttpRequest.getVerb().equals(HttpVerb.POST.name())
            || clientHttpRequest.getVerb().equals(HttpVerb.PUT.name())) {
            memory.add(0, clientHttpRequest.getBody());
        } else if (clientHttpRequest.getVerb().equals(HttpVerb.GET.name()) && memory.size() > 0) {
            httpServerResponse.setBody(memory.get(0));
        } else if (clientHttpRequest.getVerb().equals(HttpVerb.DELETE.name())) {
            memory.remove(0);
        }

        return httpServerResponse;
    }
}

package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;
import com.server.HttpVerb;

import java.util.LinkedList;

public class FormPage implements UpstreamService {
    private final ClientHttpRequest clientHttpRequest;
    private final HttpServerResponse httpServerResponse;
    private LinkedList<String> memory;

    public FormPage(
            HttpServerResponse httpServerResponse,
            ClientHttpRequest clientHttpRequest,
            LinkedList<String> memory
    ) {
        this.httpServerResponse = httpServerResponse;
        this.clientHttpRequest = clientHttpRequest;
        this.memory = memory;
    }

    public HttpServerResponse generateContent() {
        httpServerResponse.setHttpResponseCode(200);

        if (clientHttpRequest.getVerb() == HttpVerb.POST || clientHttpRequest.getVerb() == HttpVerb.PUT) {
            memory.add(0, clientHttpRequest.getBody());
        } else if (clientHttpRequest.getVerb() == HttpVerb.GET && memory.size() > 0) {
            httpServerResponse.setBody(memory.get(0));
        } else if (clientHttpRequest.getVerb() == HttpVerb.DELETE) {
            memory.remove(0);
        }

        return httpServerResponse;
    }
}

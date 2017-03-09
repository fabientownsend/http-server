package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;

import java.util.LinkedList;

public class RequestController {
    private final LinkedList<String> memory;

    public RequestController(LinkedList<String> memory) {
        this.memory = memory;
    }

    public HttpServerResponse call(HttpServerResponse httpServerResponse, ClientHttpRequest clientHttpRequest) {
        Router router = new Router();
        UpstreamService route = router.route(httpServerResponse, clientHttpRequest, memory);
        HttpServerResponse response = route.execute();

        return response;
    }
}

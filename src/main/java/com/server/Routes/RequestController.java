package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;

import java.util.LinkedList;

public class RequestController {
    private final LinkedList<String> memory;
    private final String directory;

    public RequestController(LinkedList<String> memory, String directory) {
        this.memory = memory;
        this.directory = directory;
    }

    public HttpServerResponse call(HttpServerResponse httpServerResponse, ClientHttpRequest clientHttpRequest) {
        Router router = new Router();
        BaseController route = router.route(httpServerResponse, clientHttpRequest, memory, directory);
        HttpServerResponse response = route.execute();

        return response;
    }
}

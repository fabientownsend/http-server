package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

import java.util.LinkedList;

public class RequestController {
    private final LinkedList<String> memory;
    private final String directory;

    public RequestController(LinkedList<String> memory, String directory) {
        this.memory = memory;
        this.directory = directory;
    }

    public HttpResponse call(ClientHttpRequest clientHttpRequest) {
        Router router = new Router();
        BaseController route = router.route(clientHttpRequest, memory, directory);
        HttpResponse response = route.execute();

        return response;
    }
}

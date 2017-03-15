package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

import java.util.LinkedList;

public class RequestController {
    private Router router;

    public RequestController(LinkedList<String> memory, String directory) {
        this.router = new Router(memory, directory);
    }

    public HttpResponse call(ClientHttpRequest clientHttpRequest) {
        BaseController route = router.route(clientHttpRequest.getUri());
        return route.execute(clientHttpRequest);
    }
}

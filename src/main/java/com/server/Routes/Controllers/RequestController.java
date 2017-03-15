package com.server.Routes.Controllers;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.Routes.Memory;
import com.server.Routes.Router;

public class RequestController {
    private Router router;

    public RequestController(Memory memory, String directory) {
        this.router = new Router(memory, directory);
    }

    public HttpResponse call(ClientHttpRequest clientHttpRequest) {
        BaseController route = router.route(clientHttpRequest.getUri());
        return route.execute(clientHttpRequest);
    }
}

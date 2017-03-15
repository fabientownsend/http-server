package com.server.Routes.Controllers;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.Routes.Cookie;
import com.server.Routes.Router;

import java.util.LinkedList;

public class RequestController {
    private Router router;

    public RequestController(Cookie cookie, LinkedList<String> memory, String directory) {
        this.router = new Router(cookie, memory, directory);
    }

    public HttpResponse call(ClientHttpRequest clientHttpRequest) {
        BaseController route = router.route(clientHttpRequest.getUri());
        return route.execute(clientHttpRequest);
    }
}

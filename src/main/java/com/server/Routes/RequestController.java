package com.server.Routes;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.HttpVerb;
import com.server.Routes.Controllers.BaseController;

import java.util.logging.Level;

import static com.server.HttpVerb.*;
import static com.server.Main.LOGGER;

public class RequestController {
    private Router router;

    public RequestController(Memory memory, String directory) {
        this.router = new Router(memory, directory);
    }

    public HttpResponse call(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());

        BaseController route = router.route(clientHttpRequest.getUri());

        try {
            httpResponse = executeVerb(clientHttpRequest, route);
        } catch (Exception e) {
            httpResponse.statusCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
            httpResponse.content("The server encountered an unexpected condition");
            LOGGER.log(Level.WARNING, e.toString());
        }

        return  httpResponse;
    }

    private HttpResponse executeVerb(ClientHttpRequest clientHttpRequest, BaseController route) {
        HttpVerb currentVerb = clientHttpRequest.getVerb();
        if (currentVerb == GET) {
            return route.doGet(clientHttpRequest);
        } else if (currentVerb == POST) {
            return route.doPost(clientHttpRequest);
        } else if (currentVerb == PUT) {
            return route.doPut(clientHttpRequest);
        } else if (currentVerb == DELETE) {
            return route.doDelete(clientHttpRequest);
        } else if (currentVerb == OPTIONS) {
            return route.doOptions(clientHttpRequest);
        } else if (currentVerb == PATCH) {
            return route.doPatch(clientHttpRequest);
        } else if (currentVerb == HEAD) {
            return route.doHead(clientHttpRequest);
        } else {
            HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
            httpResponse.statusCode(HttpStatusCode.METHOD_NOT_ALLOWED);
            return httpResponse;
        }
    }
}

package com.server.Routes;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.Routes.Controllers.BaseController;

import java.util.logging.Level;

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
            httpResponse = route.execute(clientHttpRequest);
        } catch (Exception e) {
            httpResponse.statusCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
            httpResponse.content("The server encountered an unexpected condition");
            LOGGER.log(Level.WARNING, e.toString());
        }

        return  httpResponse;
    }
}

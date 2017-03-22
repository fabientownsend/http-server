package com.server.Routes;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.Routes.Controllers.BaseController;
import com.server.ServerLogger;

public class RouteAction {
    private RouteProvider routeProvider;

    public RouteAction(Memory memory, String directory) {
        this.routeProvider = new RouteProvider(memory, directory);
    }

    public HttpResponse executeResult(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse;

        BaseController requiredRoute = routeProvider.getRequiredRoute(clientHttpRequest.getUri());

        try {
            httpResponse = requiredRoute.execute(clientHttpRequest);
        } catch (Exception e) {
            httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
            httpResponse.statusCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
            httpResponse.content("The server encountered an unexpected condition");
            ServerLogger.logWarning(e.getMessage());
        }

        return  httpResponse;
    }
}

package com.server.Routes;

import com.server.HttpResponse.HttpServerResponse;

public interface BaseController {
    HttpServerResponse execute();
}

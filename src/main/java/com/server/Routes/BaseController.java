package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

public interface BaseController {
    HttpResponse execute(ClientHttpRequest clientHttpRequest);
}

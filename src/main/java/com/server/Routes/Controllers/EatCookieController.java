package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.Routes.Cookie;

public class EatCookieController implements BaseController {
    private Cookie cookie;

    public EatCookieController(Cookie cookie) {
        this.cookie = cookie;
    }

    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        httpResponse.statusCode(HttpStatusCode.OK);

        String clientCookie = clientHttpRequest.getInformation("Cookie");

        if (clientCookie.equals(cookie.content())) {
            httpResponse.content("mmmm chocolate");
        }

        return httpResponse;
    }
}

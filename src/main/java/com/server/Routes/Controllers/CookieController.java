package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpHeaders;
import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.Routes.Cookie;

public class CookieController implements BaseController {
    private Cookie cookie;

    public CookieController(Cookie cookie) {
        this.cookie = cookie;
    }

    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
       HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        String queries = query(clientHttpRequest.getUri());

        cookie.setContent(changeFormat(queries));

        httpResponse.statusCode(HttpStatusCode.OK);
        httpResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.content());
        httpResponse.content("Eat");
        return httpResponse;
    }

    private String changeFormat(String query) {
        String[] queryValues = query.split("=");
        if (queryValues.length >= 2) {
            return String.join(":", queryValues[0], queryValues[1]);
        }
        else {
            return "";
        }
    }

    private String query(String uri) {
        if (uri.contains("?")) {
            return uri.split("\\?")[1];
        } else {
            return "";
        }
    }
}

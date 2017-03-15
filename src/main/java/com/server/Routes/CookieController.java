package com.server.Routes;

import com.server.HttpHeaders.HttpHeaders;
import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

import java.util.LinkedList;

public class CookieController implements BaseController {
    private final LinkedList<String> memory;

    public CookieController(LinkedList<String> memory) {
        this.memory = memory;
    }

    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
       HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        String queries = query(clientHttpRequest.getUri());

        memory.add(firstQueryAsCookie(queries));

        httpResponse.statusCode(HttpStatusCode.OK);
        httpResponse.addHeader(HttpHeaders.SET_COOKIE, firstQueryAsCookie(queries));
        httpResponse.content("Eat");
        return httpResponse;
    }

    private String firstQueryAsCookie(String query) {
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

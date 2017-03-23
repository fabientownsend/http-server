package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpHeaders;
import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.Routes.Memory;

public class CookieController implements BaseController {
    private Memory memory;

    public CookieController(Memory memory) {
        this.memory = memory;
    }

    public HttpResponse doGet(ClientHttpRequest clientHttpRequest) {
        saveQuery(clientHttpRequest);

        HttpResponse httpResponse = new HttpResponse();

        httpResponse.statusCode(HttpStatusCode.OK);
        httpResponse.addHeader(HttpHeaders.SET_COOKIE, memory.content());
        httpResponse.content("Eat");

        return httpResponse;
    }

    private void saveQuery(ClientHttpRequest clientHttpRequest) {
        String queries = query(clientHttpRequest.getUri());
        memory.setContent(changeFormat(queries));
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

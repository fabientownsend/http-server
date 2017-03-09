package com.server.Routes;

import com.server.HttpResponse.HttpServerResponse;

import java.io.File;

public class DefaultPage implements BaseController {
    private final HttpServerResponse httpServerResponse;

    public DefaultPage(HttpServerResponse httpServerResponse) {
        this.httpServerResponse = httpServerResponse;
    }

    public HttpServerResponse execute() {
        httpServerResponse.setHttpResponseCode(200);
        httpServerResponse.setHeader("Content-Type", "text/html");
        httpServerResponse.setBody(getListFiles().trim());
        return  httpServerResponse;
    }

    private String getListFiles() {
        String result = "";
        String directoryPath = "/Users/fabientownsend/Documents/Java/cob_spec/public/";
        File directory = new File(directoryPath);
        String[] files = directory.list();

        for (String file : files) {
            result += "<a href=\"/" + file + "\">" + file + "</a>\n";
        }

        return result;
    }
}

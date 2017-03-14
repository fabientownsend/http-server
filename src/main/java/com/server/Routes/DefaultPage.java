package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;

import java.io.File;

public class DefaultPage implements BaseController {
    private final HttpServerResponse httpServerResponse;
    private final String directoryPath;

    public DefaultPage(ClientHttpRequest clientHttpRequest, String directory) {
        this.httpServerResponse = new HttpServerResponse(clientHttpRequest.getHttpVersion());
        this.directoryPath = directory;
    }

    public HttpServerResponse execute() {
        httpServerResponse.setHttpResponseCode(200);
        httpServerResponse.setHeader("Content-Type", "text/html");
        httpServerResponse.setBody(getListFiles().trim());
        return  httpServerResponse;
    }

    private String getListFiles() {
        String result = "";
        File directory = new File(directoryPath);
        String[] files = directory.list();

        for (String file : files) {
            result += "<a href=\"/" + file + "\">" + file + "</a>\n";
        }

        return result;
    }
}

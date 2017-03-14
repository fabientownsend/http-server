package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

import java.io.File;

public class DefaultPage implements BaseController {
    private final HttpResponse httpResponse;
    private final String directoryPath;

    public DefaultPage(ClientHttpRequest clientHttpRequest, String directory) {
        this.httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        this.directoryPath = directory;
    }

    public HttpResponse execute() {
        httpResponse.statusCode(200);
        httpResponse.addHeader("Content-Type", "text/html");
        httpResponse.content(getListFiles().trim());
        return httpResponse;
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

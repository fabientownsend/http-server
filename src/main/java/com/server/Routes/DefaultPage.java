package com.server.Routes;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpRequest.HttpRequestParser;
import com.server.HttpResponse.HttpResponse;

import java.io.File;

public class DefaultPage implements BaseController {
    private final String directoryPath;

    public DefaultPage(String directory) {
        this.directoryPath = directory;
    }

    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        httpResponse.statusCode(HttpStatusCode.OK);
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

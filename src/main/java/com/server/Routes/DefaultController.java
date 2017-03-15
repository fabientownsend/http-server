package com.server.Routes;

import com.server.HttpHeaders.HttpHeaders;
import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;

import java.io.File;

public class DefaultController implements BaseController {
    private final String directoryPath;

    public DefaultController(String directory) {
        this.directoryPath = directory;
    }

    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        httpResponse.statusCode(HttpStatusCode.OK);
        httpResponse.addHeader(HttpHeaders.CONTENT_TYPE, "text/html");
        httpResponse.content(htmlLinksToFiles());
        return httpResponse;
    }

    private String htmlLinksToFiles() {
        String linkFiles = "";
        File directory = new File(directoryPath);
        String[] files = directory.list();

        for (String file : files) {
            if (!file.startsWith(".")) {
                linkFiles += "<a href=\"/" + file + "\">" + file + "</a>\n";
            }
        }

        return linkFiles.trim();
    }
}
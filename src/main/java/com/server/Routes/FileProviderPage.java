package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.HttpVerb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileProviderPage implements BaseController {
    private final HttpResponse httpResponse;
    private final ClientHttpRequest clientHttpRequest;
    private final String directoryPath;
    private FileProvider fileProvider = new FileProvider();

    public FileProviderPage(ClientHttpRequest clientHttpRequest, String directory) {
        this.httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        this.clientHttpRequest = clientHttpRequest;
        this.directoryPath = directory;
    }

    public HttpResponse execute() {
        if (clientHttpRequest.getVerb().equals(HttpVerb.PATCH.name())) {
            try {
                Files.write(Paths.get(directoryPath + "/patch-content.txt"), "patched content".getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpResponse.statusCode(204);
            return httpResponse;
        }

        if (!clientHttpRequest.getVerb().equals(HttpVerb.GET.name())) {
            httpResponse.statusCode(405);
            return httpResponse;
        }

        if (!clientHttpRequest.getInformation("Range").isEmpty()) {
            httpResponse.statusCode(206);
            String uri = clientHttpRequest.getUri();
            String path = directoryPath + uri.substring(1, uri.length());
            httpResponse.addHeader("Content-Type", getComment(uri));

            int[] range = clientHttpRequest.getRange();

            byte[] partialFile = fileProvider.getPartialFile(path, range[0], range[1] + 1);
            httpResponse.content(partialFile);
            return httpResponse;
        }

        httpResponse.statusCode(200);
        String uri = clientHttpRequest.getUri();
        String path = directoryPath + uri.substring(1, uri.length());

        httpResponse.addHeader("Content-Type", getComment(uri));
        httpResponse.content(fileProvider.getFullFile(path));

        return httpResponse;
    }

    private String getComment(String fileName) {
        String[] split = fileName.split("\\.");

        if (split.length >= 2) {
            if (split[1].equals("txt")) {
                return "text/plain";
            } else {
                return "image/" + split[1];
            }
        } else {
            return "text/plain";
        }
    }
}

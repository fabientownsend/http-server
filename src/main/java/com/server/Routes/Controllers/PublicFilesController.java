package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.Routes.FileProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class PublicFilesController implements BaseController {
    private final String publicDirectoryPath;
    private FileProvider fileProvider = new FileProvider();

    public PublicFilesController(String directory) {
        this.publicDirectoryPath = directory;
    }

    public HttpResponse doPatch(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        String fileName = clientHttpRequest.getUri();

        if (!fileExist(fileName)) {
            httpResponse.statusCode(HttpStatusCode.NOT_FOUND);
            return httpResponse;
        }

        return patchFile(httpResponse, fileName);
    }

    private boolean fileExist(String file) {
        return new File(publicDirectoryPath + file).exists();
    }

    private HttpResponse patchFile(HttpResponse httpResponse, String fileName)  {
        Path filePathToPatch = Paths.get(publicDirectoryPath + fileName);
        byte[] patchContent = "patched content".getBytes();

        try {
            Files.write(filePathToPatch, patchContent, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new FilePatchException("Couldn't patch the file: " + publicDirectoryPath);
        }

        httpResponse.statusCode(HttpStatusCode.NO_CONTENT);
        return httpResponse;
    }


    public HttpResponse doGet(ClientHttpRequest clientHttpRequest) {
        HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        String file = "/" + clientHttpRequest.getUri();

        if (!fileExist(file)) {
            httpResponse.statusCode(HttpStatusCode.NOT_FOUND);
            return httpResponse;
        }

        if (!clientHttpRequest.getInformation("Range").isEmpty()) {
            return returnPartialFile(clientHttpRequest, httpResponse);
        }

        return returnFullFile(clientHttpRequest, httpResponse);
    }

    private HttpResponse returnPartialFile(ClientHttpRequest clientHttpRequest, HttpResponse httpResponse) {
        httpResponse.statusCode(HttpStatusCode.PARTIAL_CONTENT);
        String uri = "/" + clientHttpRequest.getUri();
        String path = publicDirectoryPath + uri.substring(1, uri.length());
        httpResponse.addHeader("Content-Type", getComment(uri));

        int[] range = clientHttpRequest.getRange();

        byte[] partialFile = fileProvider.getPartialFile(path, range[0], range[1] + 1);
        httpResponse.content(partialFile);
        return httpResponse;
    }

    private HttpResponse returnFullFile(ClientHttpRequest clientHttpRequest, HttpResponse httpResponse) {
        httpResponse.statusCode(HttpStatusCode.OK);
        String uri = clientHttpRequest.getUri();
        String path = publicDirectoryPath + uri;

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

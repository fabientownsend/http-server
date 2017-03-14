package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;
import com.server.HttpVerb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileProvider implements BaseController {
    private final HttpServerResponse httpServerResponse;
    private final ClientHttpRequest clientHttpRequest;
    private final String directoryPath;

    public FileProvider(HttpServerResponse httpServerResponse, ClientHttpRequest clientHttpRequest, String directory) {
        this.httpServerResponse = httpServerResponse;
        this.clientHttpRequest = clientHttpRequest;
        this.directoryPath = directory;
    }

    public HttpServerResponse execute() {
        if (clientHttpRequest.getVerb().equals(HttpVerb.PATCH.name())) {
            try {
                Files.write(Paths.get(directoryPath + "/patch-content.txt"), "patched content".getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpServerResponse.setHttpResponseCode(204);
            return httpServerResponse;
        }

        if (!clientHttpRequest.getVerb().equals(HttpVerb.GET.name())) {
            httpServerResponse.setHttpResponseCode(405);
            return httpServerResponse;
        }

        if (!clientHttpRequest.getInformation("Range").isEmpty()) {
            httpServerResponse.setHttpResponseCode(206);
            String uri = clientHttpRequest.getUri();
            String path = directoryPath + uri.substring(1, uri.length());

            httpServerResponse.setHeader("Content-Type", getComment(uri));
            int[] range = clientHttpRequest.getRange();

            httpServerResponse.setBody(getRangeFile(path, range[0], range[1] + 1)); // range
            return httpServerResponse;
        }

        httpServerResponse.setHttpResponseCode(200);
        String uri = clientHttpRequest.getUri();
        String path = directoryPath + uri.substring(1, uri.length());

        httpServerResponse.setHeader("Content-Type", getComment(uri));
        httpServerResponse.setBody(getBinaryFile(path));

        return httpServerResponse;
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

    private  byte[] getRangeFile(String path, int start, int end) {
        byte[] file = getBinaryFile(path);

        if (end <= 0) {
            System.out.println("range file: " + file.length);
            end = file.length;
        }
        if (start < 0) {
            start = file.length - end + 1;
            end = file.length;
        }

        int length = end - start;
        byte[] content = new byte[length];

        for (int i = 0; i < length; i++) {
            content[i] = file[start + i];
        }
        return content;
    }

    private byte[] getBinaryFile(String directoryPath) {
        byte[] binaryImage = new byte[1];
        try {
            binaryImage = Files.readAllBytes(Paths.get(directoryPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return binaryImage;
    }
}

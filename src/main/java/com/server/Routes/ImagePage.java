package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImagePage implements UpstreamService {
    private final HttpServerResponse httpServerResponse;
    private final ClientHttpRequest clientHttpRequest;

    public ImagePage(HttpServerResponse httpServerResponse, ClientHttpRequest clientHttpRequest) {
        this.httpServerResponse = httpServerResponse;
        this.clientHttpRequest = clientHttpRequest;
    }

    public HttpServerResponse generateContent() {
        httpServerResponse.setHttpResponseCode(200);
        String directoryPath = "/Users/fabientownsend/Documents/Java/cob_spec/public/";
        String uri = clientHttpRequest.getUri();
        String path = directoryPath + uri.substring(1, uri.length());

        httpServerResponse.setHeader("Content-Type", getComment(uri));
        httpServerResponse.setBody(getImageBinary(path));

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

    private byte[] getImageBinary(String directoryPath) {
        byte[] binaryImage = new byte[1];
        try {
            binaryImage = Files.readAllBytes(Paths.get(directoryPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return binaryImage;
    }
}

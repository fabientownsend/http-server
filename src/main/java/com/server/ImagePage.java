package com.server;

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

        if (clientHttpRequest.getUri().equals("/image.jpeg")) {
            httpServerResponse.setHeader("Content-Type", "image/jpeg");
            httpServerResponse.setBody(getImageBinary(directoryPath + "image.jpeg"));
        } else if (clientHttpRequest.getUri().equals("/image.png")) {
            httpServerResponse.setHeader("Content-Type", "image/png");
            httpServerResponse.setBody(getImageBinary(directoryPath + "image.png"));
        } else if (clientHttpRequest.getUri().equals("/image.gif")) {
            httpServerResponse.setHeader("Content-Type", "image/gif");
            httpServerResponse.setBody(getImageBinary(directoryPath + "image.gif"));
        } else if (clientHttpRequest.getUri().equals("/text-file.txt")) {
            httpServerResponse.setHeader("Content-Type", "text/plain");
            httpServerResponse.setBody(getImageBinary(directoryPath + "text-file.txt"));
        } else if (clientHttpRequest.getUri().equals("/file1")) {
            httpServerResponse.setHeader("Content-Type", "text/plain");
            httpServerResponse.setBody(getImageBinary(directoryPath + "file1"));
        }


        return httpServerResponse;
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

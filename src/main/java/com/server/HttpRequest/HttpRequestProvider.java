package com.server.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;

public class HttpRequestProvider {
    private final BufferedReader socketInput;
    private final String contentLengthTitle = "Content-Length";
    private final String emptyString = "";
    private final String newLine = "\n";

    public HttpRequestProvider(BufferedReader socketInput) {
        this.socketInput = socketInput;
    }

    public String getRequest() throws IOException {
        String httpRequest = emptyString;
        httpRequest += getHeader();

        if (hasBody(httpRequest)) {
            httpRequest += getBody(bodyLength(httpRequest));
        }

        return httpRequest.trim();
    }

    private int bodyLength(String requestHeader) {
        int contentLength = 0;
        String[] headerSection = requestHeader.split("\\n");

        for (String information : headerSection) {
            String[] splitInformation = information.split(":", 2);
            if (splitInformation[0].equals(contentLengthTitle)) {
                contentLength = Integer.parseInt(splitInformation[1].trim());
            }
        }

        return contentLength;
    }

    private boolean hasBody(String requestHeader) {
        String[] headerSection = requestHeader.split("\\n");

        for (String information : headerSection) {
            String[] splitInformation = information.split(":", 2);
            if (splitInformation[0].equals(contentLengthTitle)) {
                return true;
            }
        }

        return false;
    }

    private String getHeader() {
        String temp;
        try {
            String requestHeader = socketInput.readLine() + newLine;

            while ((temp = socketInput.readLine()) != null) {
                if (temp.equals(emptyString)) {
                    break;
                }

                requestHeader += temp + newLine;
            }

            return requestHeader;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String getBody(int length) throws IOException {
        String requestBody = newLine;

        for (int i = 0; i < length; i++) {
            requestBody += (char)socketInput.read();
        }

        return requestBody;
    }
}

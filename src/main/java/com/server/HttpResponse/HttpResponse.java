package com.server.HttpResponse;

import com.server.HttpHeaders.HttpStatusCode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

public class HttpResponse {
    private final String httpVersion;
    private HttpStatusCode statusCode;
    private String header;
    private byte[] bodyImageResponse;

    public HttpResponse(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public void statusCode(HttpStatusCode httpResponseCode) {
        this.statusCode = httpResponseCode;
    }

    public void addHeader(String headerFieldName, String description) {
        this.header = headerFieldName + ": " + description;
    }

    public void content(String content) {
        content(content.getBytes());
    }

    public void content(byte[] content) {
        ByteArrayOutputStream response = new ByteArrayOutputStream();
        String header = "\r\nContent-Length: " +  content.length + "\r\n\r\n";

        try {
            response.write(header.getBytes());
            response.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.bodyImageResponse = response.toByteArray();
    }

    public byte[] response() {
        ByteArrayOutputStream response = new ByteArrayOutputStream();
        try {
            response.write(httpResponseStatusLine().getBytes());
            response.write(httpResponseHeader().getBytes());
            response.write((bodyImageResponse != null) ? bodyImageResponse : "".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toByteArray();
    }

    private String httpResponseHeader() {
        return (header != null) ? "\r\n" + header : "";
    }

    private String httpResponseStatusLine() {
        return httpVersion + " " + statusCode.getCode() + " " + statusCode.getPhrase();
    }
}

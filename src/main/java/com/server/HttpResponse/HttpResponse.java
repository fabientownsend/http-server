package com.server.HttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

public class HttpResponse {
    private final String httpVersion;
    private Integer statusCode;
    private final Hashtable<Integer, String> reasonPhrase;
    private String header;
    private byte[] bodyImageResponse;

    public HttpResponse(String httpVersion) {
        this.httpVersion = httpVersion;
        this.reasonPhrase = new Hashtable<>();
        reasonPhrase.put(200, "OK");
        reasonPhrase.put(201, "Created");
        reasonPhrase.put(204, "No Content");
        reasonPhrase.put(302, "Object Moved");
        reasonPhrase.put(400, "Bad Request");
        reasonPhrase.put(401, "Unauthorized");
        reasonPhrase.put(404, "Not Found");
        reasonPhrase.put(405, "Method Not Allowed");
        reasonPhrase.put(418, "I'm a teapot");
        reasonPhrase.put(500, "Internal Server Error");
    }

    public void statusCode(Integer httpResponseCode) {
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
        return httpVersion + " " + statusCode.toString() + " " + reasonPhrase.get(statusCode);
    }
}

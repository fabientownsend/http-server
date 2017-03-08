package com.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

public class HttpServerResponse {
    private final String httpVersion;
    private Integer statusCode;
    private final Hashtable<Integer, String> reasonPhrase;
    private String header;
    private String bodyResponse;
    private byte[] bodyImageResponse;

    public HttpServerResponse(String httpVersion) {
        this.httpVersion = httpVersion;
        this.reasonPhrase = new Hashtable<>();
        reasonPhrase.put(200, "OK");
        reasonPhrase.put(404, "Not Found");
        reasonPhrase.put(302, "Object Moved");
        reasonPhrase.put(418, "I'm a teapot");
    }

    public void setHttpResponseCode(Integer httpResponseCode) {
        this.statusCode = httpResponseCode;
    }

    public void setHeader(String headerFieldName, String description) {
        this.header = headerFieldName + ": " + description;
    }

    public void setBody(String bodyResponse) {
        this.bodyResponse = bodyResponse;
    }

    public void setBody(byte[] binaryImage) {
        ByteArrayOutputStream response = new ByteArrayOutputStream();
        String header = "\nContent-Length: " + "\n\n";

        try {
            response.write(header.getBytes());
            response.write(binaryImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.bodyImageResponse = response.toByteArray();
    }

    public byte[] build() {
        ByteArrayOutputStream response = new ByteArrayOutputStream();
        try {
            response.write(httpResponseStatusLine().getBytes());
            response.write(httpResponseHeader().getBytes());
            response.write(httpResponseBody().getBytes());
            response.write((bodyImageResponse != null) ? bodyImageResponse : "".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toByteArray();
    }

    private String httpResponseBody() {
/*        byte[] binaryBody = bodyResponse.getBytes();
        String contentLength = "\nContent-Length: " + binaryBody.length + "\n\n";
        byte[] headerBinary = contentLength.getBytes();*/

        if (bodyResponse != null) {
            //String body = "\nContent-Length: " + bodyResponse.length();
            return  "\n\n" + bodyResponse;
        } else {
            return "";
        }
    }

    private String httpResponseHeader() {
        return (header != null) ? "\n" + header : "";
    }

    private String httpResponseStatusLine() {
        return httpVersion + " " + statusCode.toString() + " " + reasonPhrase.get(statusCode);
    }
}

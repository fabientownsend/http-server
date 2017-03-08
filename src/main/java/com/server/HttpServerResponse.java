package com.server;

import java.util.Hashtable;

public class HttpServerResponse {
    private final String httpVersion;
    private Integer statusCode;
    private final Hashtable<Integer, String> reasonPhrase;
    private String header;
    private String bodyResponse;

    public HttpServerResponse(String httpVersion) {
        this.httpVersion = httpVersion;
        this.reasonPhrase = new Hashtable<>();
        reasonPhrase.put(200, "OK");
        reasonPhrase.put(404, "Not Found");
        reasonPhrase.put(302, "Object Moved");
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

    public String build() {
        String response = httpResponseStatusLine();
        response += httpResponseHeader();
        response += httpResponseBody();

        return response;
    }

    private String httpResponseBody() {
        if (bodyResponse != null) {
            String body = "\nContent-Length: " + bodyResponse.length();
            body +=  "\n\n" + bodyResponse;
            return body;
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

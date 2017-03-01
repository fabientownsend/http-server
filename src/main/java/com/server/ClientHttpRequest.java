package com.server;

public class ClientHttpRequest {
    private final String httpVerb;
    private final String uri;
    private final String httpVersionNumber;

    public ClientHttpRequest(String httpVerb, String uri, String httpVersionNumber) {
        this.httpVerb = httpVerb;
        this.uri = uri;
        this.httpVersionNumber = httpVersionNumber;
    }

    public String getHttpVerb() {
        return httpVerb;
    }

    public String getUri() {
        return uri;
    }

    public String getHttpVersionNumber() {
        return httpVersionNumber;
    }
}

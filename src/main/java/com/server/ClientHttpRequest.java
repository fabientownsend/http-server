package com.server;

import java.util.LinkedList;

public class ClientHttpRequest {
    private final String httpVerb;
    private final String uri;
    private final String httpVersionNumber;
    private LinkedList<String> memory;

    public ClientHttpRequest(String httpVerb, String uri, String httpVersionNumber) {
        this.httpVerb = httpVerb;
        this.uri = uri;
        this.httpVersionNumber = httpVersionNumber;
    }

    public ClientHttpRequest(String httpVerb, String uri, String httpVersionNumber, LinkedList<String> memory) {
        this(httpVerb, uri, httpVersionNumber);
        this.memory = memory;
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

    public String getLastData() {
        if (memory.size() > 0) {
            return memory.remove(0);
        } else {
            return "";
        }
    }

    public LinkedList<String> getData() {
        return memory;
    }
}

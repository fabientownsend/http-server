package com.server;

public class DefaultPage implements UpstreamService {
    public DefaultPage(ClientHttpRequest clientHttpRequest) {
    }

    public String generateContent() {
        return "HTTP/1.1 200 OK";
    }
}

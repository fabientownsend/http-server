package com.server;

public class NotFoundPage implements UpstreamService {
    public NotFoundPage(ClientHttpRequest clientHttpRequest) {
    }

    public String generateContent() {
        return "HTTP/1.1 404 Not Found";
    }
}

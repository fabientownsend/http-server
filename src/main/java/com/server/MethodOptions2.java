package com.server;

public class MethodOptions2 implements UpstreamService {
    public MethodOptions2(ClientHttpRequest clientHttpRequest) {
    }

    public String generateContent() {
        String response = "HTTP/1.1 200 OK";
        response += "\nAllow: GET,OPTIONS";
        return response;
    }
}

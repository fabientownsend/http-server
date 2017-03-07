package com.server;

public class FormPage implements UpstreamService {
    public FormPage(ClientHttpRequest clientHttpRequest) {
    }

    public String generateContent() {
        return "HTTP/1.1 200 OK";
    }
}

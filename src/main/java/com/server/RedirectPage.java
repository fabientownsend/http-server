package com.server;

public class RedirectPage implements UpstreamService {
    public RedirectPage(ClientHttpRequest clientHttpRequest) {
    }

    public String generateContent() {
        String response = "HTTP/1.1 302 Object moved";
        response += "\nLocation: http://localhost:5000/";
        return response;
    }
}

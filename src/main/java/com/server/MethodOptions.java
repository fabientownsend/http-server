package com.server;

public class MethodOptions implements UpstreamService {
    private final ClientHttpRequest clientHttpRequest;

    public MethodOptions(ClientHttpRequest clientHttpRequest) {
        this.clientHttpRequest = clientHttpRequest;
    }

    public String generateContent() {
        String response = "HTTP/1.1 200 OK";

        if (clientHttpRequest.getVerb() == HttpVerb.OPTIONS) {
            response += "\nAllow: GET,HEAD,POST,OPTIONS,PUT";
        } else {
            response += "\nAllow: GET,OPTIONS";
        }

        return response;
    }
}

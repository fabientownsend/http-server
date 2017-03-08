package com.server;

public class RedirectPage implements UpstreamService {
    private final ClientHttpRequest clientHttpRequest;

    public RedirectPage(ClientHttpRequest clientHttpRequest) {
        this.clientHttpRequest = clientHttpRequest;
    }

    public String generateContent() {
        HttpServerResponse httpServerResponse =
                new HttpServerResponse(clientHttpRequest.getHttpVersion());
        httpServerResponse.setHttpResponseCode(302);
        httpServerResponse.setHeader("Location", "http://localhost:5000/");
        return httpServerResponse.build();
    }
}

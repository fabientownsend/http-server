package com.server;

public class NotFoundPage implements UpstreamService {
    private final ClientHttpRequest clientHttpRequest;

    public NotFoundPage(ClientHttpRequest clientHttpRequest) {
        this.clientHttpRequest = clientHttpRequest;
    }

    public String generateContent() {
        HttpServerResponse httpServerResponse =
                new HttpServerResponse(clientHttpRequest.getHttpVersion());
        httpServerResponse.setHttpResponseCode(404);
        return httpServerResponse.build();
    }
}

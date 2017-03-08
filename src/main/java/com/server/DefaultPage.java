package com.server;

public class DefaultPage implements UpstreamService {
    private final ClientHttpRequest clientHttpRequest;

    public DefaultPage(ClientHttpRequest clientHttpRequest) {
        this.clientHttpRequest = clientHttpRequest;
    }

    public String generateContent() {
        HttpServerResponse httpServerResponse =
                new HttpServerResponse(clientHttpRequest.getHttpVersion());
        httpServerResponse.setHttpResponseCode(200);
        return  httpServerResponse.build();
    }
}

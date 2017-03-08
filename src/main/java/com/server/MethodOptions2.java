package com.server;

public class MethodOptions2 implements UpstreamService {
    private final ClientHttpRequest clientHttpRequest;

    public MethodOptions2(ClientHttpRequest clientHttpRequest) {
        this.clientHttpRequest = clientHttpRequest;
    }

    public String generateContent() {
        HttpServerResponse httpServerResponse =
                new HttpServerResponse(clientHttpRequest.getHttpVersion());
        httpServerResponse.setHttpResponseCode(200);
        httpServerResponse.setHeader("Allow", "GET,OPTIONS");
        return httpServerResponse.build();
    }
}

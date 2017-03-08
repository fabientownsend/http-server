package com.server;

public class MethodOptions implements UpstreamService {
    private final ClientHttpRequest clientHttpRequest;

    public MethodOptions(ClientHttpRequest clientHttpRequest) {
        this.clientHttpRequest = clientHttpRequest;
    }

    public String generateContent() {
        HttpServerResponse httpServerResponse =
                new HttpServerResponse(clientHttpRequest.getHttpVersion());
        httpServerResponse.setHttpResponseCode(200);

        if (clientHttpRequest.getVerb() == HttpVerb.OPTIONS) {
            httpServerResponse.setHeader("Allow", "GET,HEAD,POST,OPTIONS,PUT");
        } else {
            httpServerResponse.setHeader("Allow", "GET,OPTIONS");
        }

        return httpServerResponse.build();
    }
}

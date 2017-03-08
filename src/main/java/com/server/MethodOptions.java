package com.server;

public class MethodOptions implements UpstreamService {
    private final ClientHttpRequest clientHttpRequest;
    private final HttpServerResponse httpServerResponse;

    public MethodOptions(HttpServerResponse httpServerResponse, ClientHttpRequest clientHttpRequest) {
        this.httpServerResponse = httpServerResponse;
        this.clientHttpRequest = clientHttpRequest;
    }

    public HttpServerResponse generateContent() {
        httpServerResponse.setHttpResponseCode(200);

        if (clientHttpRequest.getVerb() == HttpVerb.OPTIONS) {
            httpServerResponse.setHeader("Allow", "GET,HEAD,POST,OPTIONS,PUT");
        } else {
            httpServerResponse.setHeader("Allow", "GET,OPTIONS");
        }

        return httpServerResponse;
    }
}

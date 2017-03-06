package com.server;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceFacotryTest {
    @Test
    public void returnsVerbGetFromHttpRequest() throws Exception {
        String simpleHttpRequest = "GET / HTTP/1.1\n"
            + "Host: localhost:5000\n"
            + "Connection: Keep-Alive\n"
            + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
            + "Accept-Encoding: gzip,deflate";

        HttpRequestParser httpRequestParser = new HttpRequestParser();
        ClientHttpRequest clientHttpRequest = httpRequestParser.parse(simpleHttpRequest);

        assertThat(clientHttpRequest.getVerb()).isEqualTo(HttpVerb.GET);
    }
}

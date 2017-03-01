package com.server;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpResponseBuilderTest {
    @Test
    public void returnsASimpleGetResponse() {
        HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
        ClientHttpRequest httpRequest = new ClientHttpRequest("GET", "/", "HTTP/1.1");
        String httpResponse = httpResponseBuilder.build(httpRequest);
        assertThat(httpResponse).isEqualTo("HTTP/1.1 200 OK");
    }

    @Test
    public void returnsPageNotFound() {
        HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
        ClientHttpRequest httpRequest = new ClientHttpRequest("GET", "/unexpected", "HTTP/1.1");
        String httpResponse = httpResponseBuilder.build(httpRequest);
        assertThat(httpResponse).isEqualTo("HTTP/1.1 404 Not Found");
    }

    @Test
    public void returnsRedirection() {
        HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
        ClientHttpRequest httpRequest = new ClientHttpRequest("GET", "/redirect", "HTTP/1.1");
        String httpResponse = httpResponseBuilder.build(httpRequest);
        assertThat(httpResponse).isEqualTo(
            "HTTP/1.1 302 Object moved\n" +
            "Location: http://localhost:5000/"
        );
    }

    @Test
    public void returnOptionWithOptionVerb() {
        HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
        ClientHttpRequest httpRequest = new ClientHttpRequest("OPTIONS", "/method_options", "HTTP/1.1");
        String httpResponse = httpResponseBuilder.build(httpRequest);
        assertThat(httpResponse).isEqualTo(
                "HTTP/1.1 200 OK\n" +
                "Allow: GET,HEAD,POST,OPTIONS,PUT"
        );
    }

    @Test
    public void returnOptionWithOptionVerbOtherUri() {
        HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
        ClientHttpRequest httpRequest = new ClientHttpRequest("OPTIONS", "/method_options2", "HTTP/1.1");
        String httpResponse = httpResponseBuilder.build(httpRequest);
        assertThat(httpResponse).isEqualTo(
                "HTTP/1.1 200 OK\n" +
                "Allow: GET,OPTIONS"
        );
    }

    @Test
    public void whenItsNotOptionsVerbOnMethodOptions() {
        HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
        ClientHttpRequest httpRequest = new ClientHttpRequest("GET", "/method_options", "HTTP/1.1");
        String httpResponse = httpResponseBuilder.build(httpRequest);
        assertThat(httpResponse).isEqualTo(
                "HTTP/1.1 200 OK\n" +
                "Allow: GET,OPTIONS"
        );
    }

    @Test
    public void notOptionButOkUri() {
        HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
        ClientHttpRequest httpRequest = new ClientHttpRequest("GET", "/method_options2", "HTTP/1.1");
        String httpResponse = httpResponseBuilder.build(httpRequest);
        assertThat(httpResponse).isEqualTo("HTTP/1.1 200 OK");
    }
}

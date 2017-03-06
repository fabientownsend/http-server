package com.server;

import org.junit.Ignore;
import org.junit.Test;

import java.util.LinkedList;

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

    @Ignore
    @Test
    public void doesntReturnDataIfNoData() {
        LinkedList<String> memory = new LinkedList<>();

        HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
        ClientHttpRequest httpRequest = new ClientHttpRequest("GET", "/form", "HTTP/1.1", memory);
        String httpResponse = httpResponseBuilder.build(httpRequest);
        assertThat(httpResponse).isEqualTo(
                "HTTP/1.1 200 OK"
        );
    }
    @Test
    public void getReturnTheLastDataIfExist() {
        LinkedList<String> memory = new LinkedList<>();
        memory.add("some data");

        HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
        ClientHttpRequest httpRequest = new ClientHttpRequest("GET", "/form", "HTTP/1.1", memory);
        String httpResponse = httpResponseBuilder.build(httpRequest);
        assertThat(httpResponse).isEqualTo(
                "HTTP/1.1 200 OK\n" +
                "Content-Length: 9\n" +
                "\n" +
                "some data"
        );
    }
}

package com.server;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ServerResponseTest {
    @Test
    public void returnsHttpServerCode() throws IOException {
        HttpServerResponse  serverResponse = new HttpServerResponse("HTTP/1.1");
        serverResponse.setHttpResponseCode(200);

        assertThat(serverResponse.build()).contains("HTTP/1.1 200 OK".getBytes());
    }

    @Test
    public void returnHttpServerHeader() throws IOException {
        HttpServerResponse  serverResponse = new HttpServerResponse("HTTP/1.1");
        serverResponse.setHttpResponseCode(200);
        serverResponse.setHeader("Content-Type", "text/plain");

        assertThat(serverResponse.build()).contains("Content-Type: text/plain".getBytes());
    }

    @Test
    public void returnsTheBodyOffTheHttpRespnse() throws IOException {
        HttpServerResponse  serverResponse = new HttpServerResponse("HTTP/1.1");
        serverResponse.setHttpResponseCode(200);
        serverResponse.setHeader("Content-Type", "text/plain");
        serverResponse.setBody("the content");

        assertThat(serverResponse.build()).contains("the content".getBytes());
    }

    @Ignore
    @Test
    public void setsTheContentLengthWhenItHasContent() throws IOException {
        HttpServerResponse  serverResponse = new HttpServerResponse("HTTP/1.1");
        serverResponse.setHttpResponseCode(200);
        serverResponse.setHeader("Content-Type", "text/plain");
        serverResponse.setBody("the content");

        assertThat(serverResponse.build()).contains("Content-Length: 11".getBytes());
    }
}

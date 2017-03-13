package com.server.HttpResponse;

import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ServerResponseTest {
    @Test
    public void returnsHttpServerCode() throws IOException {
        HttpServerResponse serverResponse = new HttpServerResponse("HTTP/1.1");
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

    @Test
    public void setsTheContentLengthWhenItHasContent() throws IOException {
        HttpServerResponse  serverResponse = new HttpServerResponse("HTTP/1.1");
        serverResponse.setHttpResponseCode(200);
        serverResponse.setHeader("Content-Type", "text/plain");
        serverResponse.setBody("the content");

        assertThat(serverResponse.build()).contains("Content-Length: 11".getBytes());
    }

    @Test
    public void canCreateAResponseWithBodyBasedOnBytes() {
        HttpServerResponse  serverResponse = new HttpServerResponse("HTTP/1.1");
        serverResponse.setHttpResponseCode(201);
        serverResponse.setHeader("Content-Type", "text/plain");
        serverResponse.setBody("201 Created");

        String expected = "HTTP/1.1 201 Created\r\n" +
                "Content-Type: text/plain\r\n" +
                "Content-Length: 11\r\n" +
                "\r\n" +
                "201 Created";

        String response = new String(serverResponse.build());

        assertThat(response).isEqualTo(expected);
    }
}

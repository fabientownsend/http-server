package com.server.HttpResponse;

import com.server.HttpHeaders.HttpStatusCode;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ServerResponseTest {
    @Test
    public void returnsHttpServerCode() throws IOException {
        HttpResponse serverResponse = new HttpResponse("HTTP/1.1");
        serverResponse.statusCode(HttpStatusCode.OK);

        assertThat(serverResponse.response()).contains("HTTP/1.1 200 OK".getBytes());
    }

    @Test
    public void returnHttpServerHeader() throws IOException {
        HttpResponse serverResponse = new HttpResponse("HTTP/1.1");
        serverResponse.statusCode(HttpStatusCode.OK);
        serverResponse.addHeader("Content-Type", "text/plain");

        assertThat(serverResponse.response()).contains("Content-Type: text/plain".getBytes());
    }

    @Test
    public void returnsTheBodyOffTheHttpRespnse() throws IOException {
        HttpResponse serverResponse = new HttpResponse("HTTP/1.1");
        serverResponse.statusCode(HttpStatusCode.OK);
        serverResponse.addHeader("Content-Type", "text/plain");
        serverResponse.content("the content");

        assertThat(serverResponse.response()).contains("the content".getBytes());
    }

    @Test
    public void setsTheContentLengthWhenItHasContent() throws IOException {
        HttpResponse serverResponse = new HttpResponse("HTTP/1.1");
        serverResponse.statusCode(HttpStatusCode.OK);
        serverResponse.addHeader("Content-Type", "text/plain");
        serverResponse.content("the content");

        assertThat(serverResponse.response()).contains("Content-Length: 11".getBytes());
    }

    @Test
    public void canCreateAResponseWithBodyBasedOnBytes() {
        HttpResponse serverResponse = new HttpResponse("HTTP/1.1");
        serverResponse.statusCode(HttpStatusCode.CREATED);
        serverResponse.addHeader("Content-Type", "text/plain");
        serverResponse.content("201 Created");

        String expected = "HTTP/1.1 201 Created\r\n" +
                "Content-Type: text/plain\r\n" +
                "Content-Length: 11\r\n" +
                "\r\n" +
                "201 Created";

        String response = new String(serverResponse.response());

        assertThat(response).isEqualTo(expected);
    }
}

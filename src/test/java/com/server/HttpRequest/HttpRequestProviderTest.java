package com.server.HttpRequest;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpRequestProviderTest {
    @Test
    public void returnsTheRequestHeader() throws  IOException {
        String simpleHttpRequest = "GET / HTTP/1.1\n"
            + "Host: localhost:5000\n"
            + "Connection: Keep-Alive\n"
            + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
            + "Accept-Encoding: gzip,deflate";

        BufferedReader socketInput = new BufferedReader(new StringReader(simpleHttpRequest));
        HttpRequestProvider httpRequestProvider = new HttpRequestProvider(socketInput);

        assertThat(httpRequestProvider.getRequest()).isEqualTo(simpleHttpRequest);
    }

    @Test
    public void returnsTheRequestHeaderWithBody() throws  IOException {
        String httpRequestWithBody = "POST /form HTTP/1.1\n"
            + "Content-Length: 11\n"
            + "Host: localhost:5000\n"
            + "Connection: Keep-Alive\n"
            + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
            + "Accept-Encoding: gzip,deflate\n"
            + "\n"
            + "data=fatcat";
        BufferedReader socketInput = new BufferedReader(new StringReader(httpRequestWithBody));
        HttpRequestProvider httpRequestProvider = new HttpRequestProvider(socketInput);

        assertThat(httpRequestProvider.getRequest()).isEqualTo(httpRequestWithBody);
    }

    @Test
    public void doesntReturnBodyIfContentLengthIsntPresent() throws  IOException {
        String httpRequestWithBody = "POST /form HTTP/1.1\n"
            + "Host: localhost:5000\n"
            + "Connection: Keep-Alive\n"
            + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
            + "Accept-Encoding: gzip,deflate\n"
            + "\n"
            + "data=fatcat";

        BufferedReader socketInput = new BufferedReader(new StringReader(httpRequestWithBody));
        HttpRequestProvider httpRequestProvider = new HttpRequestProvider(socketInput);

        assertThat(httpRequestProvider.getRequest()).isEqualTo(
            "POST /form HTTP/1.1\n"
            + "Host: localhost:5000\n"
            + "Connection: Keep-Alive\n"
            + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
            + "Accept-Encoding: gzip,deflate"
        );
    }
}

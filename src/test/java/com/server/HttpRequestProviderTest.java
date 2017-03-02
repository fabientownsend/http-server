package com.server;

import com.server.http_request.HttpRequestProvider;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class HttpRequestProviderTest {
    private String httpRequest = "POST /form HTTP/1.1\n"
            + "Content-Length: 11\n"
            + "Host: localhost:5000\n"
            + "Connection: Keep-Alive\n"
            + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
            + "Accept-Encoding: gzip,deflate\n"
            + "\n"
            + "\n"
            + "data=fatcat";

    @Test
    public void returnsTheHeaderFromAStream() throws IOException {
        BufferedReader input = new BufferedReader(new StringReader(httpRequest));
        HttpRequestProvider server = new HttpRequestProvider(input);

        assertThat(server.extractHeader()).isEqualTo(
            "POST /form HTTP/1.1\n"
            + "Content-Length: 11\n"
            + "Host: localhost:5000\n"
            + "Connection: Keep-Alive\n"
            + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
            + "Accept-Encoding: gzip,deflate\n"
        );
    }

    @Test
    public void returnANumberOfCharFromAStream() throws IOException {
        BufferedReader input = new BufferedReader(new StringReader("data=fatcat"));
        HttpRequestProvider server = new HttpRequestProvider(input);

        assertThat(server.extractBody(11)).isEqualTo("data=fatcat");
    }
}

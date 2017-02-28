package com.server;

import com.server.RequestLineParser.RequestLineFormatException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class HttpRequestParserTest {
    private String httpRequest = "PUT /form HTTP/1.1\n"
            + "Content-Length: 11\n"
            + "Host: localhost:5000\n"
            + "Connection: Keep-Alive\n"
            + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
            + "Accept-Encoding: gzip,deflate\n"
            + "\n"
            + "\"My\"=\"Data\"asdf";

    @Test
    public void returnsTheBodyOfTheRequest()  throws RequestLineFormatException {
        HttpRequestParser requestLineParser = new HttpRequestParser();
        requestLineParser.parse(httpRequest);
        assertThat(requestLineParser.getBody()).isEqualTo("\"My\"=\"Data\"asdf");
    }

    @Test
    public void returnsHttpVerb()  throws RequestLineFormatException {
        HttpRequestParser requestLineParser = new HttpRequestParser();
        requestLineParser.parse(httpRequest);
        assertThat(requestLineParser.getHttpVerb()).isEqualTo("PUT");
    }

    @Test
    public void returnsUri()  throws RequestLineFormatException {
        HttpRequestParser requestLineParser = new HttpRequestParser();
        requestLineParser.parse(httpRequest);
        assertThat(requestLineParser.getUri()).isEqualTo("/form");
    }

    @Test
    public void returnsVersionNumber()  throws RequestLineFormatException {
        HttpRequestParser requestLineParser = new HttpRequestParser();
        requestLineParser.parse(httpRequest);
        assertThat(requestLineParser.versionNumber()).isEqualTo("HTTP/1.1");
    }
}

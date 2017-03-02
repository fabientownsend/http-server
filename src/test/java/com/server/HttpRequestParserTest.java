package com.server;

import com.server.http_request.HttpRequestParser;
import com.server.http_request.RequestLineParser.RequestLineFormatException;
import org.junit.Test;

import java.util.Hashtable;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    public void returnsOptionalRequestHeader()  throws RequestLineFormatException {
        HttpRequestParser requestLineParser = new HttpRequestParser();
        requestLineParser.parse(httpRequest);
        Hashtable<String, String> optionalRequestHeader = requestLineParser.getRequestHeader(httpRequest);
        assertThat(optionalRequestHeader.get("Content-Length")).isEqualTo("11");
        assertThat(optionalRequestHeader.get("Host")).isEqualTo("localhost:5000");
    }
}

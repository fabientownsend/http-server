package com.server;

import com.server.http_request.HttpRequestParser;
import com.server.http_request.RequestLineParser.RequestLineFormatException;
import org.junit.Test;

import java.util.Hashtable;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpRequestParserTest {
    private String simpleHttpRequest = "PUT /form HTTP/1.1\n"
            + "Host: localhost:5000\n";

    @Test
    public void returnsHttpVerb()  throws RequestLineFormatException {
        HttpRequestParser httpRequestParser = new HttpRequestParser();
        httpRequestParser.parse(simpleHttpRequest);
        assertThat(httpRequestParser.getHttpVerb()).isEqualTo("PUT");
    }

    @Test
    public void returnsUri()  throws RequestLineFormatException {
        HttpRequestParser httpRequestParser = new HttpRequestParser();
        httpRequestParser.parse(simpleHttpRequest);
        assertThat(httpRequestParser.getUri()).isEqualTo("/form");
    }

    @Test
    public void returnsVersionNumber()  throws RequestLineFormatException {
        HttpRequestParser httpRequestParser = new HttpRequestParser();
        httpRequestParser.parse(simpleHttpRequest);
        assertThat(httpRequestParser.versionNumber()).isEqualTo("HTTP/1.1");
    }

    @Test
    public void returnsFalseIfTheRequestHasNoBody()  throws RequestLineFormatException {
        HttpRequestParser httpRequestParser = new HttpRequestParser();
        assertThat(httpRequestParser.hasBody(simpleHttpRequest)).isEqualTo(false);
    }

    @Test
    public void returnsTrueIfTheRequestHasBody()  throws RequestLineFormatException {
        HttpRequestParser httpRequestParser = new HttpRequestParser();
        simpleHttpRequest += "Content-Length: 11\n"
                + "Connection: Keep-Alive\n"
                + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
                + "Accept-Encoding: gzip,deflate\n";
        assertThat(httpRequestParser.hasBody(simpleHttpRequest)).isEqualTo(true);
    }

    @Test
    public void returnTheSizeOfTheContent()  throws RequestLineFormatException {
        HttpRequestParser httpRequestParser = new HttpRequestParser();
        simpleHttpRequest += "Content-Length: 11\n"
                + "Connection: Keep-Alive\n"
                + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
                + "Accept-Encoding: gzip,deflate\n";
        assertThat(httpRequestParser.contentLength(simpleHttpRequest)).isEqualTo(11);
    }

    @Test
    public void returnsZerroWhenNoContent()  throws RequestLineFormatException {
        HttpRequestParser httpRequestParser = new HttpRequestParser();
        assertThat(httpRequestParser.contentLength(simpleHttpRequest)).isEqualTo(0);
    }

    @Test
    public void returnsOptionalRequestHeader()  throws RequestLineFormatException {
        simpleHttpRequest += "Content-Length: 11\n"
            + "Connection: Keep-Alive\n"
            + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
            + "Accept-Encoding: gzip,deflate\n";
        HttpRequestParser requestLineParser = new HttpRequestParser();
        requestLineParser.parse(simpleHttpRequest);
        Hashtable<String, String> optionalRequestHeader = requestLineParser
            .getRequestHeader(simpleHttpRequest);
        assertThat(optionalRequestHeader.get("Content-Length")).isEqualTo("11");
        assertThat(optionalRequestHeader.get("Host")).isEqualTo("localhost:5000");
    }

    @Test
    public void returnsTheBodyOfTheRequest()  throws RequestLineFormatException {
        simpleHttpRequest += "\n"
                + "\"My\"=\"Data\"asdf";
        HttpRequestParser requestLineParser = new HttpRequestParser();
        requestLineParser.parse(simpleHttpRequest);
        assertThat(requestLineParser.getBody()).isEqualTo("\"My\"=\"Data\"asdf");
    }
}

package com.server;

import com.server.http_request.RequestLineParser;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import com.server.http_request.RequestLineParser.RequestLineFormatException;

public class RequestLineParserTest {
    @Test
    public void returnsDeleteHttpVerb()  throws RequestLineFormatException {
        RequestLineParser requestLineParser = new RequestLineParser();
        requestLineParser.parse("DELETE / HTTP/1.1");
        assertThat(requestLineParser.getHttpVerb()).isEqualTo("DELETE");
    }

    @Test
    public void returnsGetHttpVerb()  throws RequestLineFormatException {
        RequestLineParser requestLineParser = new RequestLineParser();
        requestLineParser.parse("GET / HTTP/1.1");
        assertThat(requestLineParser.getHttpVerb()).isEqualTo("GET");
    }

    @Test
    public void returnsHeadHttpVerb()  throws RequestLineFormatException {
        RequestLineParser requestLineParser = new RequestLineParser();
        requestLineParser.parse("HEAD / HTTP/1.1");
        assertThat(requestLineParser.getHttpVerb()).isEqualTo("HEAD");
    }

    @Test
    public void returnsPostHttpVerb()  throws RequestLineFormatException {
        RequestLineParser requestLineParser = new RequestLineParser();
        requestLineParser.parse("POST / HTTP/1.1");
        assertThat(requestLineParser.getHttpVerb()).isEqualTo("POST");
    }

    @Test
    public void returnsPutHttpVerb()  throws RequestLineFormatException {
        RequestLineParser requestLineParser = new RequestLineParser();
        requestLineParser.parse("PUT / HTTP/1.1");
        assertThat(requestLineParser.getHttpVerb()).isEqualTo("PUT");
    }

    @Test
    public void returnsTheUri()  throws RequestLineFormatException {
        RequestLineParser requestLineParser = new RequestLineParser();
        requestLineParser.parse("HEAD /logs HTTP/1.1");
        assertThat(requestLineParser.getUri()).isEqualTo("/logs");
    }

    @Test
    public void returnTheVersionNumber()  throws RequestLineFormatException {
        RequestLineParser requestLineParser = new RequestLineParser();
        requestLineParser.parse("HEAD / HTTP/1.1");
        assertThat(requestLineParser.versionNumber()).isEqualTo("HTTP/1.1");
    }

    @Test
    public void throwsAnExceptionWhenNumberOffArgumentsIsntThree() {
        RequestLineParser requestLineParser = new RequestLineParser();

        assertThatExceptionOfType(RequestLineFormatException.class).isThrownBy(
                () -> requestLineParser.parse("")
        );
    }

    @Test
    public void throwsAnExceptionIfHttpVerbDoesntExist()  throws RequestLineFormatException {
        RequestLineParser requestLineParser = new RequestLineParser();

        assertThatExceptionOfType(RequestLineFormatException.class).isThrownBy(
                () -> requestLineParser.parse("HELLO / HTTP/1.1")
        );
    }

    @Test
    public void throwsAnExceptionIfHttpVerbIsntUpperCase()  throws RequestLineFormatException {
        RequestLineParser requestLineParser = new RequestLineParser();

        assertThatExceptionOfType(RequestLineFormatException.class).isThrownBy(
                () -> requestLineParser.parse("get / HTTP/1.1")
        );
    }
}

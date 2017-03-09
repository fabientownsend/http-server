package com.server.HttpRequest;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpRequest.HttpRequestParser;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpRequestParserTest {
    @Test
    public void returnsVerbGetFromHttpRequest() throws Exception {
        String simpleHttpRequest = "GET / HTTP/1.1\n"
            + "Host: localhost:5000\n"
            + "Connection: Keep-Alive\n"
            + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
            + "Accept-Encoding: gzip,deflate";

        HttpRequestParser httpRequestParser = new HttpRequestParser();
        ClientHttpRequest clientHttpRequest = httpRequestParser.parse(simpleHttpRequest);

        assertThat(clientHttpRequest.getVerb()).isEqualTo(HttpVerb.GET);
    }

    @Test
    public void returnsVerbPostFromHttpRequest() throws Exception {
        String simpleHttpRequest = "POST / HTTP/1.1\n"
            + "Host: localhost:5000\n"
            + "Connection: Keep-Alive\n"
            + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
            + "Accept-Encoding: gzip,deflate";

        HttpRequestParser httpRequestParser = new HttpRequestParser();
        ClientHttpRequest clientHttpRequest = httpRequestParser.parse(simpleHttpRequest);

        assertThat(clientHttpRequest.getVerb()).isEqualTo(HttpVerb.POST);
    }

    @Test
    public void returnsUriFromHttpRequest() throws Exception {
        String simpleHttpRequest = "POST / HTTP/1.1\n"
                + "Host: localhost:5000\n"
                + "Connection: Keep-Alive\n"
                + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
                + "Accept-Encoding: gzip,deflate";

        HttpRequestParser httpRequestParser = new HttpRequestParser();
        ClientHttpRequest clientHttpRequest = httpRequestParser.parse(simpleHttpRequest);

        assertThat(clientHttpRequest.getUri()).isEqualTo("/");
    }

    @Test
    public void returnsHttpVersionFromHttpRequest() throws Exception {
        String simpleHttpRequest = "POST / HTTP/1.1\n"
                + "Host: localhost:5000\n"
                + "Connection: Keep-Alive\n"
                + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
                + "Accept-Encoding: gzip,deflate";

        HttpRequestParser httpRequestParser = new HttpRequestParser();
        ClientHttpRequest clientHttpRequest = httpRequestParser.parse(simpleHttpRequest);

        assertThat(clientHttpRequest.getHttpVersion()).isEqualTo("HTTP/1.1");
    }

    @Test
    public void returnsInformationFromHttpHeaderSection() throws Exception {
        String simpleHttpRequest = "POST / HTTP/1.1\n"
                + "Host: localhost:5000\n"
                + "Connection: Keep-Alive\n"
                + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
                + "Accept-Encoding: gzip,deflate";

        HttpRequestParser httpRequestParser = new HttpRequestParser();
        ClientHttpRequest clientHttpRequest = httpRequestParser.parse(simpleHttpRequest);

        assertThat(clientHttpRequest.getInformation("Host")).isEqualTo("localhost:5000");
    }

    @Test
    public void returnsBinaryDataFromBody() throws Exception {
        String httpRequestWithBody = "POST /form HTTP/1.1\n"
            + "Content-Length: 11\n"
            + "Host: localhost:5000\n"
            + "Connection: Keep-Alive\n"
            + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
            + "Accept-Encoding: gzip,deflate\n"
            + "\n"
            + "data=fatcat";
        HttpRequestParser httpRequestParser = new HttpRequestParser();
        ClientHttpRequest clientHttpRequest = httpRequestParser.parse(httpRequestWithBody);

        assertThat(clientHttpRequest.getBody()).isEqualTo("data=fatcat");
    }
}

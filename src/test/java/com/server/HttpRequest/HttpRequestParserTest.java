package com.server.HttpRequest;

import com.server.BadRequestException;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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

    @Test
    public void throwExcpetionWhenLineRequestHasntThreeElements() {
        String httpRequestWithBody = "GET HTTP/1.1\n"
                + "Content-Length: 11\n"
                + "Host: localhost:5000\n"
                + "Connection: Keep-Alive\n"
                + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
                + "Accept-Encoding: gzip,deflate\n"
                + "\n"
                + "data=fatcat";
        HttpRequestParser httpRequestParser = new HttpRequestParser();

        assertThatExceptionOfType(BadRequestException.class).isThrownBy(
                () -> httpRequestParser.parse(httpRequestWithBody)
        );
    }

    @Test
    public void returnsRangesWhenStartAndEnd() {
        String httpRequestWithBody = "GET / HTTP/1.1\n"
                + "Content-Length: 11\n"
                + "Host: localhost:5000\n"
                + "Connection: Keep-Alive\n"
                + "Range: bytes=0-4\n"
                //+ "Range: bytes=-6\n"
                + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
                + "Accept-Encoding: gzip,deflate\n"
                + "\n"
                + "data=fatcat";
        HttpRequestParser httpRequestParser = new HttpRequestParser();
        ClientHttpRequest clientHttpRequest = httpRequestParser.parse(httpRequestWithBody);

        int[] range = new int[2];
        range[0] = 0;
        range[1] = 4;
        assertThat(clientHttpRequest.getRange()).isEqualTo(range);
    }

    @Test
    public void returnRangeWithOnlyStart() {
        String httpRequestWithBody = "GET / HTTP/1.1\n"
                + "Content-Length: 11\n"
                + "Host: localhost:5000\n"
                + "Connection: Keep-Alive\n"
                //+ "Range: bytes=-6\n"
                + "Range: bytes=4-\n"
                + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
                + "Accept-Encoding: gzip,deflate\n"
                + "\n"
                + "data=fatcat";
        HttpRequestParser httpRequestParser = new HttpRequestParser();
        ClientHttpRequest clientHttpRequest = httpRequestParser.parse(httpRequestWithBody);

        int[] range = new int[2];
        range[0] = 4;
        range[1] = -100;
        assertThat(clientHttpRequest.getRange()).isEqualTo(range);
    }

    @Test
    public void returnRangeWithOnlyEnd() {
        String httpRequestWithBody = "GET / HTTP/1.1\n"
                + "Content-Length: 11\n"
                + "Host: localhost:5000\n"
                + "Connection: Keep-Alive\n"
                + "Range: bytes=-6\n"
                + "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n"
                + "Accept-Encoding: gzip,deflate\n"
                + "\n"
                + "data=fatcat";
        HttpRequestParser httpRequestParser = new HttpRequestParser();
        ClientHttpRequest clientHttpRequest = httpRequestParser.parse(httpRequestWithBody);

        int[] range = new int[2];
        range[0] = -100;
        range[1] = 6;
        assertThat(clientHttpRequest.getRange()).isEqualTo(range);
    }
}

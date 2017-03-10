package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NotFoundPageTest {
    private HttpServerResponse httpServerResponse = new HttpServerResponse("HTTP/1.1");

    @Test
    public void returnsNotFoundPageHeader() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());

        NotFoundPage notFoundPage = new NotFoundPage(httpServerResponse);
        assertThat(notFoundPage.execute().build()).contains("HTTP/1.1 404 Not Found".getBytes());
    }
}

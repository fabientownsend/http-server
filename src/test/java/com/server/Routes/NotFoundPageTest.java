package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NotFoundPageTest {
    @Test
    public void returnsNotFoundPageHeader() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());
        clientHttpRequest.setHttpVersion("HTTP/1.1");

        NotFoundPage notFoundPage = new NotFoundPage(clientHttpRequest);
        assertThat(notFoundPage.execute().response()).contains("HTTP/1.1 404 Not Found".getBytes());
    }
}

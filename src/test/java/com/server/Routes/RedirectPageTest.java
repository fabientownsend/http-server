package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RedirectPageTest {
    private HttpServerResponse httpServerResponse = new HttpServerResponse("HTTP/1.1");

    @Test
    public void returnsRedirectionHeader() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());

        RedirectPage redirectPage = new RedirectPage(httpServerResponse);
        assertThat(redirectPage.execute().build()).contains("HTTP/1.1 302 Object Moved".getBytes());
    }
}

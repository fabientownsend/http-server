package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RedirectPageTest {
    @Test
    public void returnsRedirectionHeader() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());
        clientHttpRequest.setHttpVersion("HTTP/1.1");

        RedirectPage redirectPage = new RedirectPage(clientHttpRequest);
        assertThat(redirectPage.execute().response()).contains("HTTP/1.1 302 Object Moved".getBytes());
    }
}

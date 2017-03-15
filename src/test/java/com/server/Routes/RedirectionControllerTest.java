package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RedirectionControllerTest {
    @Test
    public void returnsRedirectionHeader() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());
        clientHttpRequest.setHttpVersion("HTTP/1.1");

        RedirectionController redirectionController = new RedirectionController();
        assertThat(redirectionController.execute(clientHttpRequest).response()).contains("HTTP/1.1 302 Object Moved".getBytes());
    }
}

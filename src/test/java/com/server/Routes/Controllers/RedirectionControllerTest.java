package com.server.Routes.Controllers;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RedirectionControllerTest {
    @Test
    public void returnsRedirectionHeader() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET);
        clientHttpRequest.setHttpVersion("HTTP/1.1");

        RedirectionController redirectionController = new RedirectionController();
        String httpResponse = new String(redirectionController.doGet(clientHttpRequest).response());
        assertThat(httpResponse).isEqualTo(
            "HTTP/1.1 302 Object Moved\r\n" +
            "Location: http://localhost:5000/"
        );
    }
}

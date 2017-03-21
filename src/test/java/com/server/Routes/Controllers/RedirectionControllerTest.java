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
        String httpResponse = new String(redirectionController.execute(clientHttpRequest).response());
        assertThat(httpResponse).isEqualTo(
            "HTTP/1.1 302 Object Moved\r\n" +
            "Location: http://localhost:5000/"
        );
    }

    @Test
    public void returnsMethodNotAllowedForOtherRequests() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        TeaController teaController = new TeaController();
        String httpResponse;

        for (HttpVerb verb : HttpVerb.values()) {
            if (verb != HttpVerb.GET) {
                clientHttpRequest.setVerb(verb);
                clientHttpRequest.setHttpVersion("HTTP/1.1");

                httpResponse = new String(teaController.execute(clientHttpRequest).response());
                assertThat(httpResponse).isEqualTo("HTTP/1.1 405 Method Not Allowed");
            }
        }
    }
}

package com.server.Routes.Controllers;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TeaControllerTest {
    @Test
    public void returnOkWhenGetRequest() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET);
        clientHttpRequest.setHttpVersion("HTTP/1.1");

        TeaController teaController = new TeaController();
        String httpResponse = new String(teaController.execute(clientHttpRequest).response());
        assertThat(httpResponse).isEqualTo("HTTP/1.1 200 OK");
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

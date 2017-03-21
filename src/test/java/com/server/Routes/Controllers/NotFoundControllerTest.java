package com.server.Routes.Controllers;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NotFoundControllerTest {
    @Test
    public void returnsNotFoundPageHeader() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET);
        clientHttpRequest.setHttpVersion("HTTP/1.1");

        NotFoundController notFoundController = new NotFoundController();
        assertThat(notFoundController.doGet(clientHttpRequest).response()).contains("HTTP/1.1 404 Not Found".getBytes());
    }
}

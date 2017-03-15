package com.server.Routes.Controllers;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import com.server.Routes.Controllers.NotFoundController;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NotFoundControllerTest {
    @Test
    public void returnsNotFoundPageHeader() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());
        clientHttpRequest.setHttpVersion("HTTP/1.1");

        NotFoundController notFoundController = new NotFoundController();
        assertThat(notFoundController.execute(clientHttpRequest).response()).contains("HTTP/1.1 404 Not Found".getBytes());
    }
}

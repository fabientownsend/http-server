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
        String httpResponse = new String(teaController.doGet(clientHttpRequest).response());
        assertThat(httpResponse).isEqualTo("HTTP/1.1 200 OK");
    }
}

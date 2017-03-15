package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TeaControllerTest {
    @Test
    public void returnTeaRedirection() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());
        clientHttpRequest.setHttpVersion("HTTP/1.1");

        TeaController teaController = new TeaController();
        assertThat(teaController.execute(clientHttpRequest).response()).contains("HTTP/1.1 200 OK".getBytes());
    }
}

package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TeaTest {
    private HttpServerResponse httpServerResponse = new HttpServerResponse("HTTP/1.1");

    @Test
    public void returnTeaRedirection() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());

        Tea tea = new Tea(httpServerResponse);
        assertThat(tea.execute().build()).contains("HTTP/1.1 200 OK".getBytes());
    }
}

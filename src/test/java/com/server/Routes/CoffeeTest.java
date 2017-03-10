package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CoffeeTest {
    private HttpServerResponse httpServerResponse = new HttpServerResponse("HTTP/1.1");

    @Test
    public void returnsCoffeeHeader() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());

        Coffee coffee = new Coffee(httpServerResponse);
        assertThat(coffee.execute().build()).contains("HTTP/1.1 418 I'm a teapot".getBytes());
        assertThat(coffee.execute().build()).contains("I'm a teapot".getBytes());
    }
}

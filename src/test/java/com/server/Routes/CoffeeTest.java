package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CoffeeTest {
    @Test
    public void returnsCoffeeHeader() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());
        clientHttpRequest.setHttpVersion("HTTP/1.1");

        Coffee coffee = new Coffee(clientHttpRequest);
        assertThat(coffee.execute().response()).contains("HTTP/1.1 418 I'm a teapot".getBytes());
        assertThat(coffee.execute().response()).contains("I'm a teapot".getBytes());
    }
}

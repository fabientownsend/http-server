package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CoffeeControllerTest {
    private ClientHttpRequest clientHttpRequest;
    private CoffeeController coffeeController;

    @Before
    public void initialize() {
        this.clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());
        clientHttpRequest.setHttpVersion("HTTP/1.1");
        this.coffeeController = new CoffeeController();
    }

    @Test
    public void returns418HttpCode() {
        String httpResponse = new String(coffeeController.execute(clientHttpRequest).response());

        assertThat(httpResponse).contains("HTTP/1.1 418 I'm a teapot");
    }

    @Test
    public void returnsContentPage() {
        String httpResponse = new String(coffeeController.execute(clientHttpRequest).response());

        assertThat(httpResponse).contains("I'm a teapot");
    }
}

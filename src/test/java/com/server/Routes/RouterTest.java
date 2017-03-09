package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;
import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class RouterTest {
    private LinkedList <String> memory = new LinkedList<>();
    private Router requestController = new Router();
    private HttpServerResponse httpServerResponse = new HttpServerResponse("HTTP/1.1");

    @Test
    public void returnsDefaultPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/");

        assertThat(requestController.route(httpServerResponse, clientHttpRequest, memory)).isInstanceOf(DefaultPage.class);
    }

    @Test
    public void returnsFormPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/form");

        assertThat(requestController.route(httpServerResponse, clientHttpRequest, memory)).isInstanceOf(FormPage.class);
    }

    @Test
    public void returnsRedirectPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/redirect");

        assertThat(requestController.route(httpServerResponse, clientHttpRequest, memory)).isInstanceOf(RedirectPage.class);
    }

    @Test
    public void returnsMethodOptionsPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/method_options");

        assertThat(requestController.route(httpServerResponse, clientHttpRequest, memory)).isInstanceOf(MethodOptions.class);
    }

    @Test
    public void returnsMethodOptionsTwoPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/method_options2");

        assertThat(requestController.route(httpServerResponse, clientHttpRequest, memory)).isInstanceOf(MethodOptions2.class);
    }

    @Test
    public void returnsNotFoundPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/it_doesnt_exist");

        assertThat(requestController.route(httpServerResponse, clientHttpRequest, memory)).isInstanceOf(NotFoundPage.class);
    }
}

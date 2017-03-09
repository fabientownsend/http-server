package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;
import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceFactoryTest {
    private LinkedList <String> memory = new LinkedList<>();
    private ServiceFactory serviceFactory = new ServiceFactory(memory);
    private HttpServerResponse httpServerResponse = new HttpServerResponse("HTTP/1.1");

    @Test
    public void returnsDefaultPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/");

        assertThat(serviceFactory.provide(httpServerResponse, clientHttpRequest)).isInstanceOf(DefaultPage.class);
    }

    @Test
    public void returnsFormPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/form");

        assertThat(serviceFactory.provide(httpServerResponse, clientHttpRequest)).isInstanceOf(FormPage.class);
    }

    @Test
    public void returnsRedirectPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/redirect");

        assertThat(serviceFactory.provide(httpServerResponse, clientHttpRequest)).isInstanceOf(RedirectPage.class);
    }

    @Test
    public void returnsMethodOptionsPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/method_options");

        assertThat(serviceFactory.provide(httpServerResponse, clientHttpRequest)).isInstanceOf(MethodOptions.class);
    }

    @Test
    public void returnsMethodOptionsTwoPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/method_options2");

        assertThat(serviceFactory.provide(httpServerResponse, clientHttpRequest)).isInstanceOf(MethodOptions2.class);
    }

    @Test
    public void returnsNotFoundPage() throws Exception {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/it_doesnt_exist");

        assertThat(serviceFactory.provide(httpServerResponse, clientHttpRequest)).isInstanceOf(NotFoundPage.class);
    }
}

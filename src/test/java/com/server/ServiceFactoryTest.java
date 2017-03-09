package com.server;

import org.junit.Test;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceFactoryTest {
    private LinkedList <String> memory = new LinkedList<>();
    private HttpServerResponse httpServerResponse = new HttpServerResponse("HTTP/1.1");

    @Test
    public void returnsDefaultPage() throws Exception {
        ServiceFactory serviceFactory = new ServiceFactory();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/");

        assertThat(serviceFactory.provide(httpServerResponse, clientHttpRequest, memory)).isInstanceOf(DefaultPage.class);
    }

    @Test
    public void returnsFormPage() throws Exception {
        ServiceFactory serviceFactory = new ServiceFactory();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/form");

        assertThat(serviceFactory.provide(httpServerResponse, clientHttpRequest, memory)).isInstanceOf(FormPage.class);
    }

    @Test
    public void returnsRedirectPage() throws Exception {
        ServiceFactory serviceFactory = new ServiceFactory();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/redirect");

        assertThat(serviceFactory.provide(httpServerResponse, clientHttpRequest, memory)).isInstanceOf(RedirectPage.class);
    }

    @Test
    public void returnsMethodOptionsPage() throws Exception {
        ServiceFactory serviceFactory = new ServiceFactory();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/method_options");

        assertThat(serviceFactory.provide(httpServerResponse, clientHttpRequest, memory)).isInstanceOf(MethodOptions.class);
    }

    @Test
    public void returnsMethodOptionsTwoPage() throws Exception {
        ServiceFactory serviceFactory = new ServiceFactory();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/method_options2");

        assertThat(serviceFactory.provide(httpServerResponse, clientHttpRequest, memory)).isInstanceOf(MethodOptions2.class);
    }

    @Test
    public void returnsNotFoundPage() throws Exception {
        ServiceFactory serviceFactory = new ServiceFactory();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/it_doesnt_exist");

        assertThat(serviceFactory.provide(httpServerResponse, clientHttpRequest, memory)).isInstanceOf(NotFoundPage.class);
    }
}

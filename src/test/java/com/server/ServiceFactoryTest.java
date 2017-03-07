package com.server;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ServiceFactoryTest {
    @Test
    public void returnsDefaultPage() throws Exception {
        ServiceFactory serviceFactory = new ServiceFactory();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/");

        assertThat(serviceFactory.provide(clientHttpRequest)).isInstanceOf(DefaultPage.class);
    }

    @Test
    public void returnsFormPage() throws Exception {
        ServiceFactory serviceFactory = new ServiceFactory();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/form");

        assertThat(serviceFactory.provide(clientHttpRequest)).isInstanceOf(FormPage.class);
    }

    @Test
    public void returnsRedirectPage() throws Exception {
        ServiceFactory serviceFactory = new ServiceFactory();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/redirect");

        assertThat(serviceFactory.provide(clientHttpRequest)).isInstanceOf(RedirectPage.class);
    }

    @Test
    public void returnsMethodOptionsPage() throws Exception {
        ServiceFactory serviceFactory = new ServiceFactory();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/method_options");

        assertThat(serviceFactory.provide(clientHttpRequest)).isInstanceOf(MethodOptions.class);
    }

    @Test
    public void returnsMethodOptionsTwoPage() throws Exception {
        ServiceFactory serviceFactory = new ServiceFactory();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/method_options2");

        assertThat(serviceFactory.provide(clientHttpRequest)).isInstanceOf(MethodOptions2.class);
    }

    @Test
    public void returnsNotFoundPage() throws Exception {
        ServiceFactory serviceFactory = new ServiceFactory();
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setUri("/it_doesnt_exist");

        assertThat(serviceFactory.provide(clientHttpRequest)).isInstanceOf(NotFoundPage.class);
    }
}

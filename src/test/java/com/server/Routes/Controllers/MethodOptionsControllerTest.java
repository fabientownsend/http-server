package com.server.Routes.Controllers;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MethodOptionsControllerTest {
    private ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

    @Test
    public void returnsOptionsWhenOptionVerb() {
        clientHttpRequest.setVerb(HttpVerb.OPTIONS);

        MethodOptionsController methodOptions = new MethodOptionsController();
        assertThat(methodOptions.doOptions(clientHttpRequest).response()).contains("Allow: GET,HEAD,POST,OPTIONS,PUT".getBytes());
    }

    @Test
    public void returnsOptionsWhenGetVerb() {
        clientHttpRequest.setVerb(HttpVerb.GET);

        MethodOptionsController methodOptions = new MethodOptionsController();
        assertThat(methodOptions.doGet(clientHttpRequest).response()).contains("Allow: GET,OPTIONS".getBytes());
    }

    @Test
    public void returnsOptionsWhenPostVerb() {
        clientHttpRequest.setVerb(HttpVerb.POST);

        MethodOptionsController methodOptions = new MethodOptionsController();
        assertThat(methodOptions.doPost(clientHttpRequest).response()).contains("Allow: GET,OPTIONS".getBytes());
    }

    @Test
    public void returnsOptionsWhenPutVerb() {
        clientHttpRequest.setVerb(HttpVerb.PUT);

        MethodOptionsController methodOptions = new MethodOptionsController();
        assertThat(methodOptions.doPut(clientHttpRequest).response()).contains("Allow: GET,OPTIONS".getBytes());
    }
}

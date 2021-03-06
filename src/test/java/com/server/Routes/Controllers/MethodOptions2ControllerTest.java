package com.server.Routes.Controllers;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MethodOptions2ControllerTest {
    private ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

    @Test
    public void returnsOptionsWhenOptionVerb() {
        clientHttpRequest.setVerb(HttpVerb.OPTIONS);

        MethodOptions2Controller methodOptions = new MethodOptions2Controller();
        assertThat(methodOptions.doOptions(clientHttpRequest).response()).contains("Allow: GET,OPTIONS".getBytes());
    }

    @Test
    public void returnsOptionsWhenGetVerb() {
        clientHttpRequest.setVerb(HttpVerb.GET);

        MethodOptions2Controller methodOptions = new MethodOptions2Controller();
        assertThat(methodOptions.doGet(clientHttpRequest).response()).contains("Allow: GET,OPTIONS".getBytes());
    }

    @Test
    public void returnsOptionsWhenPostVerb() {
        clientHttpRequest.setVerb(HttpVerb.POST);

        MethodOptions2Controller methodOptions = new MethodOptions2Controller();
        assertThat(methodOptions.doPost(clientHttpRequest).response()).contains("Allow: GET,OPTIONS".getBytes());
    }

    @Test
    public void returnsOptionsWhenPutVerb() {
        clientHttpRequest.setVerb(HttpVerb.PUT);

        MethodOptions2Controller methodOptions = new MethodOptions2Controller();
        assertThat(methodOptions.doPut(clientHttpRequest).response()).contains("Allow: GET,OPTIONS".getBytes());
    }
}

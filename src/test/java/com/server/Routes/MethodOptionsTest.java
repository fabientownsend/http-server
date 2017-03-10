package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpServerResponse;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MethodOptionsTest {
    private HttpServerResponse httpServerResponse = new HttpServerResponse("HTTP/1.1");
    private ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

    @Test
    public void returnsOptionsWhenOptionVerb() {
        clientHttpRequest.setVerb(HttpVerb.OPTIONS.name());

        MethodOptions methodOptions = new MethodOptions(httpServerResponse, clientHttpRequest);
        assertThat(methodOptions.execute().build()).contains("Allow: GET,HEAD,POST,OPTIONS,PUT".getBytes());
    }

    @Test
    public void returnsOptionsWhenGetVerb() {
        clientHttpRequest.setVerb(HttpVerb.GET.name());

        MethodOptions methodOptions = new MethodOptions(httpServerResponse, clientHttpRequest);
        assertThat(methodOptions.execute().build()).contains("Allow: GET,OPTIONS".getBytes());
    }

    @Test
    public void returnsOptionsWhenPostVerb() {
        clientHttpRequest.setVerb(HttpVerb.POST.name());

        MethodOptions methodOptions = new MethodOptions(httpServerResponse, clientHttpRequest);
        assertThat(methodOptions.execute().build()).contains("Allow: GET,OPTIONS".getBytes());
    }

    @Test
    public void returnsOptionsWhenPutVerb() {
        clientHttpRequest.setVerb(HttpVerb.PUT.name());

        MethodOptions methodOptions = new MethodOptions(httpServerResponse, clientHttpRequest);
        assertThat(methodOptions.execute().build()).contains("Allow: GET,OPTIONS".getBytes());
    }
}
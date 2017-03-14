package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TeaTest {
    private HttpResponse httpResponse = new HttpResponse("HTTP/1.1");

    @Test
    public void returnTeaRedirection() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());

        Tea tea = new Tea(httpResponse);
        assertThat(tea.execute().response()).contains("HTTP/1.1 200 OK".getBytes());
    }
}

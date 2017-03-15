package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TeaTest {
    @Test
    public void returnTeaRedirection() {
        ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
        clientHttpRequest.setVerb(HttpVerb.GET.name());
        clientHttpRequest.setHttpVersion("HTTP/1.1");

        Tea tea = new Tea();
        assertThat(tea.execute(clientHttpRequest).response()).contains("HTTP/1.1 200 OK".getBytes());
    }
}

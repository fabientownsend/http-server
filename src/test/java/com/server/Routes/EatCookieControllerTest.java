package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class EatCookieControllerTest {
    private LinkedList memoryServer;
    private ClientHttpRequest clientHttpRequest;
    private HashMap headerInfo;

    @Before
    public void initialization() {
        this.clientHttpRequest = new ClientHttpRequest();
        this.clientHttpRequest.setVerb(HttpVerb.GET.name());
        this.clientHttpRequest.setHttpVersion("HTTP/1.1");
        this.headerInfo = new HashMap<>();
        this.memoryServer = new LinkedList<>();
    }

    @Test
    public void returnsMessageWhenUserCookieIdSimilar() {
        headerInfo.put("Cookie", "user id");
        memoryServer.add("user id");
        clientHttpRequest.setSectionInformation(headerInfo);
        EatCookieController coffee = new EatCookieController(memoryServer);

        String httpResponse  = new String(coffee.execute(clientHttpRequest).response());

        assertThat(httpResponse).contains("mmmm chocolat");
    }

    @Test
    public void returnsMessageWhenUserCookieIdDifferent() {
        headerInfo.put("Cookie", "user id");
        memoryServer.add("user id different");
        clientHttpRequest.setSectionInformation(headerInfo);
        EatCookieController coffee = new EatCookieController(memoryServer);

        String httpResponse  = new String(coffee.execute(clientHttpRequest).response());

        assertThat(httpResponse).doesNotContain("mmmm chocolat");
    }
}

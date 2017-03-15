package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultPageTest {
    private ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
    private String directoryPath = "/Users/fabientownsend/Documents/Java/server/";

    @Test
    public void returnTheListingOfDirectory() {
        clientHttpRequest.setVerb(HttpVerb.GET.name());

        DefaultPage defaultPage = new DefaultPage(directoryPath);

        assertThat(defaultPage.execute(clientHttpRequest).response()).contains("response.gradle".getBytes());
        assertThat(defaultPage.execute(clientHttpRequest).response()).contains("README.md".getBytes());
    }

    @Test
    public void contrainLinksOfFiles() {
        clientHttpRequest.setVerb(HttpVerb.GET.name());

        DefaultPage defaultPage = new DefaultPage(directoryPath);

        assertThat(defaultPage.execute(clientHttpRequest).response()).contains("<a href=\"/response.gradle\">response.gradle</a>".getBytes());
    }
}

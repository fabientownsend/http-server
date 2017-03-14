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

        DefaultPage defaultPage = new DefaultPage(clientHttpRequest, directoryPath);

        assertThat(defaultPage.execute().build()).contains("build.gradle".getBytes());
        assertThat(defaultPage.execute().build()).contains("README.md".getBytes());
    }

    @Test
    public void contrainLinksOfFiles() {
        clientHttpRequest.setVerb(HttpVerb.GET.name());

        DefaultPage defaultPage = new DefaultPage(clientHttpRequest, directoryPath);

        assertThat(defaultPage.execute().build()).contains("<a href=\"/build.gradle\">build.gradle</a>".getBytes());
    }
}

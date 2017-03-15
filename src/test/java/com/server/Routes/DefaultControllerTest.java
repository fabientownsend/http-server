package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultControllerTest {
    private ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
    private String directoryPath = "/Users/fabientownsend/Documents/Java/server/src/test/java/com/server";

    @Test
    public void displaysListOfFiles() {
        clientHttpRequest.setVerb(HttpVerb.GET.name());

        DefaultController defaultController = new DefaultController(directoryPath);
        String httpResponse = new String(defaultController.execute(clientHttpRequest).response());

        assertThat(httpResponse).contains("ServerTest.java");
        assertThat(httpResponse).contains("ServerSettingsParserTest.java");
    }

    @Test
    public void displaysLinksToEachFiles() {
        clientHttpRequest.setVerb(HttpVerb.GET.name());

        DefaultController defaultController = new DefaultController(directoryPath);
        String httpResponse = new String(defaultController.execute(clientHttpRequest).response());

        assertThat(httpResponse).contains("<a href=\"/ServerTest.java\">ServerTest.java</a>");
    }
}

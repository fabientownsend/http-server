package com.server.Routes.Controllers;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultControllerTest {
    private ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
    private Path currentPath = Paths.get("");
    private String directoryPath =  currentPath.toAbsolutePath().toString();

    @Test
    public void displaysListOfFiles() {
        clientHttpRequest.setVerb(HttpVerb.GET);

        DefaultController defaultController = new DefaultController(directoryPath);
        String httpResponse = new String(defaultController.execute(clientHttpRequest).response());

        assertThat(httpResponse).contains("build.gradle");
        assertThat(httpResponse).contains(".travis.yml");
    }

    @Test
    public void displaysLinksToEachFiles() {
        clientHttpRequest.setVerb(HttpVerb.GET);

        DefaultController defaultController = new DefaultController(directoryPath);
        String httpResponse = new String(defaultController.execute(clientHttpRequest).response());

        assertThat(httpResponse).contains("<a href=\"/build.gradle\">build.gradle</a>");
    }
}

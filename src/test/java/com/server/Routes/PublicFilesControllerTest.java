package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class PublicFilesControllerTest {
    private ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
    private String directoryPath = "/Users/fabientownsend/Documents/Java/server/";

    @Before
    public void initialize() {
        clientHttpRequest.setVerb(HttpVerb.GET.name());
        clientHttpRequest.setHttpVersion("HTTP/1.1");
    }

    @Test
    public void return200WhenFileFound() {
        clientHttpRequest.setUri("/build.gradle");

        PublicFilesController defaultPage = new PublicFilesController(directoryPath);
        String httpResponse = new String(defaultPage.execute(clientHttpRequest).response());

        assertThat(httpResponse).contains("HTTP/1.1 200 OK");
    }

    @Test
    public void return404WhenFileNotFound() {
        clientHttpRequest.setUri("/asdfjkl;as");

        PublicFilesController defaultPage = new PublicFilesController(directoryPath);
        String httpResponse = new String(defaultPage.execute(clientHttpRequest).response());

        assertThat(httpResponse).contains("HTTP/1.1 404 Not Found");
    }

    @Test
    public void returnNotAllowedWhenNotGetOrPatch() {
        clientHttpRequest.setUri("/build.gradle");
        clientHttpRequest.setVerb(HttpVerb.OPTIONS.name());

        PublicFilesController defaultPage = new PublicFilesController(directoryPath);
        String httpResponse = new String(defaultPage.execute(clientHttpRequest).response());

        assertThat(httpResponse).contains("HTTP/1.1 405 Method Not Allowed");
    }

    @Test
    public void returnsBinaryFilesRequested() {
        clientHttpRequest.setUri("/build.gradle");

        PublicFilesController defaultPage = new PublicFilesController(directoryPath);
        byte[] binaryFile = getBinaryFile(directoryPath + "/build.gradle");

        assertThat(defaultPage.execute(clientHttpRequest).response()).contains(binaryFile);
    }

    private byte[] getBinaryFile(String directoryPath) {
        byte[] binaryImage = new byte[1];
        try {
            binaryImage = Files.readAllBytes(Paths.get(directoryPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return binaryImage;
    }
}

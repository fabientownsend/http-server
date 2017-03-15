package com.server.Routes;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.HttpVerb;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class FileProviderPageTest {
    private HttpResponse httpResponse = new HttpResponse("HTTP/1.1");
    private ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
    private String directoryPath = "/Users/fabientownsend/Documents/Java/server/";

    @Test
    public void returnTheListingOfDirectory() {
        clientHttpRequest.setVerb(HttpVerb.GET.name());
        clientHttpRequest.setUri("/response.gradle");

        FileProviderPage defaultPage = new FileProviderPage(directoryPath);

        assertThat(defaultPage.execute(clientHttpRequest).response()).contains(getBinaryFile(directoryPath + "/response.gradle"));
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

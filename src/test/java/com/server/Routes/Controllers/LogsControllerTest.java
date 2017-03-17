package com.server.Routes.Controllers;

import com.server.HttpHeaders.HttpHeaders;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Before;
import org.junit.Test;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LogsControllerTest {
    private ClientHttpRequest clientHttpRequest = new ClientHttpRequest();
    private Map<String, String> header = new HashMap<>();

    @Before
    public void initialize() {
        clientHttpRequest.setVerb(HttpVerb.GET.name());
    }

    @Test
    public void returnsUnauthorizedWhenNoAuthenticateInHeader() {
        clientHttpRequest.setHttpVersion("HTTP/1.1");
        LogsController logsControllerPage = new LogsController();
        String response = new String(logsControllerPage.execute(clientHttpRequest).response());
        assertThat(response).isEqualTo(
            "HTTP/1.1 401 Unauthorized\r\n" +
            HttpHeaders.WWW_AUTHENTICATE + ": Basic realm=\"User Visible Realm\"");
    }

    @Test
    public void returnsUnauthorizedWhenWrongPassword() {
        String base64LoginPassword = new String(Base64.getEncoder().encode("admin:lol".getBytes()));
        header.put(HttpHeaders.AUTHORIZATION, "Basic " + base64LoginPassword);
        clientHttpRequest.setSectionInformation(header);
        clientHttpRequest.setHttpVersion("HTTP/1.1");

        LogsController logsControllerPage = new LogsController();
        String response = new String(logsControllerPage.execute(clientHttpRequest).response());
        assertThat(response).contains(
            "HTTP/1.1 401 Unauthorized\r\n" +
            HttpHeaders.WWW_AUTHENTICATE + ": Basic realm=\"User Visible Realm\"");
    }

    @Test
    public void returnsUnauthorizedWhenWrongLogin() {
        String base64LoginPassword = new String(Base64.getEncoder().encode("lol:hunter2".getBytes()));
        header.put(HttpHeaders.AUTHORIZATION, "Basic " + base64LoginPassword);
        clientHttpRequest.setSectionInformation(header);
        clientHttpRequest.setHttpVersion("HTTP/1.1");

        LogsController logsControllerPage = new LogsController();
        String response = new String(logsControllerPage.execute(clientHttpRequest).response());
        assertThat(response).contains(
            "HTTP/1.1 401 Unauthorized\r\n" +
            HttpHeaders.WWW_AUTHENTICATE + ": Basic realm=\"User Visible Realm\"");
    }

    @Test
    public void returnsOkWhenCorrectAuthentication() {
        String base64LoginPassword = new String(Base64.getEncoder().encode("admin:hunter2".getBytes()));
        header.put(HttpHeaders.AUTHORIZATION, "Basic " + base64LoginPassword);
        clientHttpRequest.setSectionInformation(header);
        clientHttpRequest.setHttpVersion("HTTP/1.1");

        LogsController logsControllerPage = new LogsController();
        String response = new String(logsControllerPage.execute(clientHttpRequest).response());
        assertThat(response).contains(
            "HTTP/1.1 200 OK\r\n" +
            HttpHeaders.WWW_AUTHENTICATE + ": Basic YWRtaW46aHVudGVyMg==");
    }

    @Test
    public void returnsLogsRequests() {
        String base64LoginPassword = new String(Base64.getEncoder().encode("admin:hunter2".getBytes()));
        header.put(HttpHeaders.AUTHORIZATION, "Basic " + base64LoginPassword);
        clientHttpRequest.setSectionInformation(header);
        clientHttpRequest.setHttpVersion("HTTP/1.1");

        LogsController logsControllerPage = new LogsController();
        String response = new String(logsControllerPage.execute(clientHttpRequest).response());
        assertThat(response).contains("GET /logs HTTP/1.1");
    }
}

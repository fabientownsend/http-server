package com.server.Routes;

import com.server.HttpHeaders.HttpHeaders;
import com.server.HttpHeaders.HttpStatusCode;
import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpResponse.HttpResponse;
import com.server.HttpVerb;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Level;

import static com.server.Main.LOGGER;

public class LogsController implements BaseController {
    private static final String LOGS_PATH ="/Users/fabientownsend/Documents/Java/server/logs/logger.log";
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "hunter2";

    public HttpResponse execute(ClientHttpRequest clientHttpRequest) {
           HttpResponse httpResponse = new HttpResponse(clientHttpRequest.getHttpVersion());
        if (clientHttpRequest.getVerb().equals(HttpVerb.GET.name())) {

            String authentication = authentication(clientHttpRequest);
            if (isAuthenticated(authentication)) {
                httpResponse.statusCode(HttpStatusCode.OK);
                httpResponse.addHeader(HttpHeaders.WWW_AUTHENTICATE, authentication);
                httpResponse.content(logsHttpRequests());
            } else {
                httpResponse.statusCode(HttpStatusCode.UNAUTHORIZED);
                httpResponse.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"User Visible Realm\"");
            }
        }

        return httpResponse;
    }

    private String authentication(ClientHttpRequest clientHttpRequest) {
        return clientHttpRequest.getInformation(HttpHeaders.AUTHORIZATION);
    }

    private boolean isAuthenticated(String authentication) {
        return !authentication.isEmpty() && isAdmin(extractBase64Auth(authentication));
    }

    private String extractBase64Auth(String authentication) {
        return authentication.split("\\s")[1];
    }

    private boolean isAdmin(String base64Auth) {
        final String authentication = new String(Base64.getDecoder().decode(base64Auth));
        final String[] splitAuthentication = authentication.split(":");
        final String login  =  splitAuthentication[0];
        final String password  =  splitAuthentication[1];
        return login.equals(ADMIN_LOGIN) && password.equals(ADMIN_PASSWORD);
    }

    private String logsHttpRequests() {
        String httpRequests = "";

        try {
            FileReader fileReader = new FileReader(LOGS_PATH);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            httpRequests = getHttpRequest(bufferReader);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.WARNING, "can't find log file. " + e.getMessage());
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "error when reading log file. " + e.getMessage());
        }

        return httpRequests;
    }

    private String getHttpRequest(BufferedReader bufferReader) throws IOException {
        String httpRequests = "";
        String logLIne;

        while ((logLIne = bufferReader.readLine()) != null) {
            if (logLIne.startsWith("INFO: request: ")) {
                httpRequests += logLIne;
            }
        }

        return httpRequests;
    }
}

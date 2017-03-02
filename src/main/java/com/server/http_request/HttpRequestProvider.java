package com.server.http_request;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequestProvider {
    private BufferedReader input;

    public HttpRequestProvider(BufferedReader input) {
        this.input = input;
    }

    public String extractHeader() throws IOException {
        String temp;
        String header = "";

        while ((temp = input.readLine()) != null) {
            if (temp.equals("")) {
                break;
            }
            header += temp + "\n";
        }

        return header;
    }

    public String extractBody(int length) throws IOException {
        String body = "";
        for (int i = 0; i < length; i++) {
            body += (char)input.read();
        }
        return body;
    }
}

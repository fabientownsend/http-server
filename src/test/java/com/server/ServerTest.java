package com.server;

import org.junit.Test;

import java.io.*;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ServerTest {
    @Test
    public void returnErrorMessageWhenBadRequest() {
        BufferedReader input = new BufferedReader(new StringReader("BAD request"));
        StringWriter out = new StringWriter();
        PrintWriter output = new PrintWriter(out, true);
        Server server = new Server(input, output);
        server.run();

        assertThat(out.toString()).isEqualTo("error request\n");
    }

    @Test
    public void returnTheRequestWhenCorrect() {
        BufferedReader input = new BufferedReader(new StringReader("GET / HTTP/1.1"));
        StringWriter out = new StringWriter();
        PrintWriter output = new PrintWriter(out, true);
        Server server = new Server(input, output);
        server.run();

        assertThat(out.toString()).isEqualTo("HTTP/1.1 200 OK\n");
    }
}

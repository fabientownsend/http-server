package com.server;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

public class ServerTest {
    @Test
    public void returnsAlways200() {
        BufferedReader socketInput = new BufferedReader(new StringReader("GET / HTTP/1.1"));
        StringWriter output = new StringWriter();
        PrintWriter socketOutput = new PrintWriter(output, true);

        Server server = new Server(socketInput, socketOutput, new LinkedList<>());
        server.start();
        assertThat(output.toString()).isEqualTo("HTTP/1.1 200 OK\n");
    }
}

package com.server;

import org.junit.Test;

import java.io.*;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ServerTest {
    @Test
    public void returnTheInput() {
        BufferedReader input = new BufferedReader(new StringReader("Coucou"));
        StringWriter out = new StringWriter();
        PrintWriter output = new PrintWriter(out, true);
        Server server = new Server(input, output);
        server.run();

        assertThat(out.toString()).contains("Coucou");
    }
}

package com.server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerLogger.initialise();

        ServerSettingsParser serverSettingsParser = new ServerSettingsParser(args);

        Server server = new Server(serverSettingsParser.getPort(), serverSettingsParser.getDirectory());
        server.start();
    }
}

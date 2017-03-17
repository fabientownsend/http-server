package com.server;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerSettingsParser {
    private final String[] serverSettings;
    private int port = 1;
    private final int optionPort = port - 1;
    private final int directory = 3;
    private final int optionDirectory = directory - 1;

    public ServerSettingsParser(String[] settings) {
        this.serverSettings = settings;
    }

    public ServerSettingsParser() {
        this.serverSettings = new String[4];
        this.serverSettings[0] = "-p";
        this.serverSettings[1] = "5000";
        this.serverSettings[2] = "-d";
        this.serverSettings[3] = "/Users/fabientownsend/Documents/Java/cob_spec/public/";
    }

    public Integer getPort() {
        Integer port;

        if (!optionPort()) {
            throw new IllegalArgumentException();
        }

        try {
            port = Integer.parseInt(serverSettings[this.port]);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        return port;
    }

    private boolean optionPort() {
        return serverSettings[optionPort].equals("-p");
    }

    public String getDirectory() {
        if (!optionDirectory()) {
            throw new IllegalArgumentException();
        }

        return serverSettings[directory];
    }

    private boolean optionDirectory() {
        return serverSettings[optionDirectory].equals("-d");
    }
}

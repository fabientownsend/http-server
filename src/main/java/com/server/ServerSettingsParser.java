package com.server;

public class ServerSettingsParser {
    private String[] serverSettings;
    private int defaultPort = 5000;
    private String defaultDirectory = "";

    public void parse(String[] command) {
        this.serverSettings = command;
    }

    public int getPort() {
        if (hasSettings()) {
            return defaultPort;
        } else {
            return Integer.parseInt(serverSettings[1]);
        }
    }

    public String getDirectory() {
        if (hasSettings()) {
            return defaultDirectory;
        } else {
            return serverSettings[3];
        }
    }

    private boolean hasSettings() {
        return serverSettings.length == 0;
    }
}

package com.server;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class ServerSettingsParserTest {
    @Test
    public void returnThePortOfTheCommand() {
        ServerSettingsParser serverSettingsParser = new ServerSettingsParser();
        String[] serverSettings = getServerSettings(
                "5000",
                "/Users/fabientownsend/Documents/Java/cob_spec/public/"
        );

        serverSettingsParser.parse(serverSettings);
        try {
            assertThat(serverSettingsParser.getPort()).isEqualTo(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void defaultPortWhenArgsIsEmpty() throws Exception {
        ServerSettingsParser serverSettingsParser = new ServerSettingsParser();
        String[] serverSettings = new String[0];

        serverSettingsParser.parse(serverSettings);
        assertThat(serverSettingsParser.getPort()).isEqualTo(5000);
    }


    @Test
    public void returnTheDirectory() {
        ServerSettingsParser serverSettingsParser = new ServerSettingsParser();
        String[] serverSettings = getServerSettings(
                "5000",
                "/Users/fabientownsend/Documents/Java/cob_spec/public/"
        );

        serverSettingsParser.parse(serverSettings);
        assertThat(serverSettingsParser.getDirectory())
                .isEqualTo("/Users/fabientownsend/Documents/Java/cob_spec/public/");
    }

    @Test
    public void defaultDirectoryIsEmpty() throws Exception {
        ServerSettingsParser serverSettingsParser = new ServerSettingsParser();
        String[] serverSettings = new String[0];

        serverSettingsParser.parse(serverSettings);
        assertThat(serverSettingsParser.getDirectory())
                .isEqualTo("");
    }

    @Test
    public void throwErrorWhenPortIsntANumber() throws Exception {
        ServerSettingsParser serverSettingsParser = new ServerSettingsParser();
        String[] serverSettings = getServerSettings(
                "asdf",
                "/Users/fabientownsend/Documents/Java/cob_spec/public/"
        );

        serverSettingsParser.parse(serverSettings);

        assertThatExceptionOfType(NumberFormatException.class).isThrownBy(
            () -> serverSettingsParser.getPort()
        );
    }

    private String[] getServerSettings(String port, String directory) {
        String[] args = new String[4];
        args[0] = "-p";
        args[1] = port;
        args[2] = "-d";
        args[3] = directory;
        return args;
    }
}

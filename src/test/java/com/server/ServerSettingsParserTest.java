package com.server;

import org.junit.Test;
import  static org.assertj.core.api.Assertions.*;

public class ServerSettingsParserTest {
    @Test
    public void returnsPortFromSettings() {
        String[] serverSettings = { "-p", "5000", "-d", "User/name/folder" };
        ServerSettingsParser serverSettingsParser = new ServerSettingsParser(serverSettings);
        assertThat(serverSettingsParser.getPort()).isEqualTo(5000);
    }

    @Test
    public void returnDirectoryFromSettings() {
        String[] serverSettings = { "-p", "5000", "-d", "User/name/folder" };
        ServerSettingsParser serverSettingsParser = new ServerSettingsParser(serverSettings);
        assertThat(serverSettingsParser.getDirectory()).isEqualTo("User/name/folder");
    }

    @Test
    public void raisesAnExceptionWhenPortIsntANumber() {
        String[] serverSettings = { "-p", "asdf", "-d", "User/name/folder" };
        ServerSettingsParser serverSettingsParser = new ServerSettingsParser(serverSettings);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
            () -> serverSettingsParser.getPort()
        );
    }

    @Test
    public void raisesAnExceptionIfPortIsntPrecededByPortOption() {
        String[] serverSettings = { "-d", "5000", "-d", "User/name/folder" };
        ServerSettingsParser serverSettingsParser = new ServerSettingsParser(serverSettings);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
            () -> serverSettingsParser.getPort()
        );
    }

    @Test
    public void raisesAnExceptionIfDirectoryIsntPrecededByDirectoryOption() {
        String[] serverSettings = { "-p", "5000", "-p", "User/name/folder" };
        ServerSettingsParser serverSettingsParser = new ServerSettingsParser(serverSettings);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
            () -> serverSettingsParser.getDirectory()
        );
    }
}

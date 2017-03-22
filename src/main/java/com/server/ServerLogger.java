package com.server;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ServerLogger {
    public final static Logger LOGGER = Logger.getLogger(Server.class.getName());

    public static void initialise() throws IOException {
        File dir = new File("logs");
        dir.mkdir();
        File yourFile = new File("logs/logger.log");
        yourFile.createNewFile();

        FileHandler fileHandle = new FileHandler("logs/logger.log", false);
        fileHandle.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fileHandle);
    }

    public static void logWarning(String message) {
        LOGGER.log(Level.WARNING, message);
    }

    public static void logInfo(String s) {
        LOGGER.log(Level.INFO, s);
    }
}

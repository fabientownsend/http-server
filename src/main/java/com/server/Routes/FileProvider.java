package com.server.Routes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileProvider {
    public  byte[] getPartialFile(String path, int startAt, int stopAt) {
        //https://sourcemaking.com/refactoring/replace-method-with-method-object
        // RangeCalculation(this).compute();
        byte[] fullFile = getFullFile(path);

        if (stopAt <= 0) {
            stopAt = fullFile.length;
        }

        if (startAt < 0) {
            startAt = fullFile.length - stopAt + 1;
            stopAt = fullFile.length;
        }

        int partialFileSize = stopAt - startAt;
        byte[] partialFile = new byte[partialFileSize];

        for (int i = 0; i < partialFileSize; i++) {
            partialFile[i] = fullFile[startAt + i];
        }

        return partialFile;
    }

    public byte[] getFullFile(String directoryPath) {
        byte[] file = new byte[1];

        try {
            file = Files.readAllBytes(Paths.get(directoryPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}

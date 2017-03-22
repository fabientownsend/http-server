package com.server.Routes.Controllers;

import com.server.HttpRequest.ClientHttpRequest;
import com.server.HttpVerb;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PublicFilesControllerTest {
    private ClientHttpRequest clientHttpRequest = new ClientHttpRequest();

    @Before
    public void initialize() {
        clientHttpRequest.setVerb(HttpVerb.GET);
        clientHttpRequest.setHttpVersion("HTTP/1.1");
    }

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void return200WhenFileFound() throws IOException {
        final File createdFile = folder.newFile("temp.txt");
        clientHttpRequest.setUri("/temp.txt");
        PublicFilesController defaultPage = new PublicFilesController(createdFile.getParent());

        String httpResponse = new String(defaultPage.doGet(clientHttpRequest).response());

        assertThat(httpResponse).contains("HTTP/1.1 200 OK");
    }

    @Test
    public void return404WhenFileNotFound() throws IOException {
        final File createdFile = folder.newFile("temp.txt");
        clientHttpRequest.setUri("/nonexistent_file");

        PublicFilesController defaultPage = new PublicFilesController(createdFile.getParent());
        String httpResponse = new String(defaultPage.doGet(clientHttpRequest).response());

        assertThat(httpResponse).contains("HTTP/1.1 404 Not Found");
    }

    @Test
    public void returnNotAllowedWhenNotGetOrPatch() throws IOException {
        final File createdFile = folder.newFile("temp.txt");
        clientHttpRequest.setUri("/temp.txt");
        clientHttpRequest.setVerb(HttpVerb.OPTIONS);

        PublicFilesController defaultPage = new PublicFilesController(createdFile.getParent());
        String httpResponse = new String(defaultPage.doOptions(clientHttpRequest).response());

        assertThat(httpResponse).contains("HTTP/1.1 405 Method Not Allowed");
    }

    @Test
    public void raiseFilePatchExceptionWhenCantPatchFile() throws IOException {
        final File createdFile = folder.newFile("temp.txt");
        createdFile.setReadOnly();
        clientHttpRequest.setUri("/temp.txt");
        PublicFilesController defaultPage = new PublicFilesController(createdFile.getParent());

        assertThatExceptionOfType(FilePatchException.class).isThrownBy(
            () -> defaultPage.doPatch(clientHttpRequest)
        );
    }

    @Test
    public void returns404WhenPatchFileDoesntExist() throws IOException {
        final File createdFile = folder.newFile("temp.txt");
        clientHttpRequest.setUri("/nonexistent_file");
        PublicFilesController defaultPage = new PublicFilesController(createdFile.getParent());

        String httpResponse = new String(defaultPage.doPatch(clientHttpRequest).response());

        assertThat(httpResponse).contains("HTTP/1.1 404 Not Found");
    }

    @Test
    public void patchTheFile() throws IOException {
        final File createdFile = folder.newFile("temp.txt");

        final File fileReference = getFileWithContent("patched content");

        clientHttpRequest.setUri("/temp.txt");
        PublicFilesController defaultPage = new PublicFilesController(createdFile.getParent());

        defaultPage.doPatch(clientHttpRequest).response();

        assertThat(createdFile.compareTo(fileReference));
    }

    private File getFileWithContent(String fileContent) throws IOException {
        final File fileReference = folder.newFile("temp2.txt");
        FileWriter fw = new FileWriter(fileReference.getPath());
        BufferedWriter bf = new BufferedWriter(fw);
        bf.write(fileContent);
        bf.close();
        return fileReference;
    }
}

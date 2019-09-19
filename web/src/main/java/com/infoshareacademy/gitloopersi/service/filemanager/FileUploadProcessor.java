package com.infoshareacademy.gitloopersi.service.filemanager;

import com.infoshareacademy.gitloopersi.exception.HolidaysFileNotFound;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.Part;

@RequestScoped
public class FileUploadProcessor {

  private static final String SETTINGS_FILE = "settings.properties";

  public File uploadHolidaysFile(Part filePart) throws IOException, HolidaysFileNotFound {

    String fileName = Paths.get(filePart.getSubmittedFileName())
        .getFileName().toString();

    if (fileName == null || fileName.isEmpty()) {
      throw new HolidaysFileNotFound("No file holidays has been uploaded");
    }

    File file = new File(getUploadFilePath() + fileName);
    Files.deleteIfExists(file.toPath());

    InputStream fileContent = filePart.getInputStream();

    Files.copy(fileContent, file.toPath());

    fileContent.close();

    return file;
  }

  public String getUploadFilePath() throws IOException {
    Properties settings = new Properties();
    settings.load(Thread.currentThread()
        .getContextClassLoader().getResource(SETTINGS_FILE)
        .openStream());
    return settings.getProperty("Upload.Path.Holidays");
  }
}
package com.example.test.controller;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        if (fileName==null){
            fileName="image.png";
        }
        fileName= fileName.replaceAll("\\s", "");
        fileName= fileName.replaceAll("'", "");
        fileName= fileName.replaceAll("é", "");
        fileName= fileName.replaceAll("è", "");
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}

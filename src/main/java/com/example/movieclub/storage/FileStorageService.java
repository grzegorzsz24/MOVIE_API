package com.example.movieclub.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileStorageService {
    private final String fileStorageLocation;
    private final String imageStorageLocation;

    public FileStorageService(@Value("${app.storage.location}") String storageLocation) throws FileNotFoundException {
        this.fileStorageLocation = storageLocation + "/files/";
        this.imageStorageLocation = storageLocation + "/img/";
        Path fileStoragePath = Path.of(this.fileStorageLocation);
        checkDirectoryExists(fileStoragePath);
        Path imageStoragePath = Path.of(this.imageStorageLocation);
        checkDirectoryExists(imageStoragePath);
    }

    private void checkDirectoryExists(Path path) throws FileNotFoundException {
        if (Files.notExists(path)) {
            throw new FileNotFoundException("Directory %s does not exist.".formatted(path.toString()));
        }
    }
}

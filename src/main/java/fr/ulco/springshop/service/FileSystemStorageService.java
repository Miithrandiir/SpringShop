package fr.ulco.springshop.service;

import fr.ulco.springshop.properties.StorageProperty;
import fr.ulco.springshop.service.core.StorageServiceInterface;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;
import java.util.stream.Stream;

public class FileSystemStorageService implements StorageServiceInterface {

    private final Path rootLocation;

    public static FileSystemStorageService create(StorageProperty properties) {
        Path rootLocation = Paths.get(properties.getLocation());
        return new FileSystemStorageService(rootLocation);
    }

    private FileSystemStorageService(Path rootLocation) {
        this.rootLocation = rootLocation;
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage");
        }
    }

    @Override
    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new FileSystemException("Failed to store empty file");
            }
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null)
                throw new FileSystemException("Failed to store without name");

            originalFilename = UUID.randomUUID() + "-" + originalFilename;

            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(originalFilename))
                    .normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new FileSystemException(
                        "Cannot store file outside current directory.");
            }

            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            return originalFilename;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file");
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read stored files");
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            UrlResource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileSystemException("Could not read file: " + filename);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}

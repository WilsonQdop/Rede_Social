package br.com.wilsonqdop.redesocial.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class UploadFilesService {

    private final Path fileRoot = Paths.get("Uploads_imagens");

    public String saveImagesFiles(MultipartFile file) {
        try {
            if(!Files.exists(fileRoot)) {
                Files.createDirectories(fileRoot);
            }

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path targetPath = fileRoot.resolve(fileName);

            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            return targetPath.toString();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o arquivo");
        }
    }
}

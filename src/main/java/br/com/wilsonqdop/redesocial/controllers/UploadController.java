package br.com.wilsonqdop.redesocial.controllers;

import br.com.wilsonqdop.redesocial.domain.user.User;
import br.com.wilsonqdop.redesocial.repositories.UserRepository;
import br.com.wilsonqdop.redesocial.service.UploadFilesService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("upload")
@CrossOrigin("*")
public class UploadController {

    private final UploadFilesService uploadFilesService;
    private final UserRepository userRepository;

    public UploadController(UploadFilesService uploadFilesService, UserRepository userRepository) {
        this.uploadFilesService = uploadFilesService;
        this.userRepository = userRepository;
    }

    @PostMapping("/file")
    public ResponseEntity<String> saveImagesFiles(@RequestParam("file")MultipartFile file, @AuthenticationPrincipal User user) {

        String fileName = this.uploadFilesService.saveImagesFiles(file);

        user.setProfilePictureUrl(fileName);
        this.userRepository.save(user);

        return ResponseEntity.ok().build();

    }
}

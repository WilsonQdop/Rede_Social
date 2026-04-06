package br.com.wilsonqdop.redesocial.infra.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get("Uploads_imagens");
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        // Tudo que começar com /files/ vai buscar dentro da pasta Uploads_imagens
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:" + uploadPath + "/");
    }
}

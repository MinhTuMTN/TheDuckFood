package com.theduckfood.api;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/api/file")
public class FileAPI {
    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> getImage(@RequestParam("id") String id) throws IOException {
        try {
            File file = new File("C:\\Images\\" + id);
            byte[] bytes = Files.readAllBytes(file.toPath());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytes);
        } catch (Exception e) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(null);
        }
    }


    public static String uploadImage (MultipartFile image) {
        try {
            String extension = "";

            int i = Objects.requireNonNull(image.getOriginalFilename()).lastIndexOf('.');
            if (i > 0) {
                extension = "." + image.getOriginalFilename().substring(i + 1);
            }

            String fileName = String.valueOf((new Date()).getTime()) + extension;
            File outputFile = new File("C:\\Images\\" + fileName);
            image.transferTo(outputFile);

            return fileName;
        } catch (IOException e) {
            return null;
        }
    }

}

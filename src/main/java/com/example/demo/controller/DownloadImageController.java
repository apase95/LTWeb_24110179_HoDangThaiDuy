package com.example.demo.controller;

import com.example.demo.util.Constant;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;

@RestController
public class DownloadImageController {

    @GetMapping("/image")
    public ResponseEntity<Resource> image(@RequestParam String fname) throws Exception {
        for (String folder : new String[]{"categories", "products", "avatars"}) {
            Path folderPath = Path.of(Constant.UPLOAD_DIR, folder).toAbsolutePath().normalize();
            Path file = folderPath.resolve(fname).normalize();
            if (file.startsWith(folderPath) && Files.isRegularFile(file)) {
                String contentType = Files.probeContentType(file);
                MediaType mediaType = contentType == null
                        ? MediaType.APPLICATION_OCTET_STREAM
                        : MediaType.parseMediaType(contentType);
                return ResponseEntity.ok().contentType(mediaType).body(new UrlResource(file.toUri()));
            }
        }
        return ResponseEntity.notFound().build();
    }
}

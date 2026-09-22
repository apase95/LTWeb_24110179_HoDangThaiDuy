package com.example.demo.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;
    private final boolean enabled;

    public CloudinaryService(@Value("${CLOUDINARY_CLOUD_NAME:}") String cloudName,
                             @Value("${CLOUDINARY_API_KEY:}") String apiKey,
                             @Value("${CLOUDINARY_API_SECRET:}") String apiSecret,
                             @Value("${UPLOAD_USE_CLOUDINARY:false}") boolean enabled) {
        this.enabled = enabled;
        this.cloudinary = new Cloudinary(Map.of("cloud_name", cloudName, "api_key", apiKey, "api_secret", apiSecret));
    }

    public boolean enabled() { return enabled; }

    public String upload(MultipartFile file, String folder) throws IOException {
        if (file == null || file.isEmpty()) return null;
        String type = file.getContentType();
        if (type == null || !type.startsWith("image/")) throw new IllegalArgumentException("Chỉ cho phép upload ảnh");
        Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), Map.of("folder", folder));
        return String.valueOf(result.get("secure_url"));
    }
}

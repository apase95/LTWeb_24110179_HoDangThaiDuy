package com.example.demo.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class FileUploadUtil {

    private static final String UPLOAD_DIR = Constant.UPLOAD_DIR;

    public static String saveFile(MultipartFile file, String subFolder) throws IOException {
        String uploadPath = UPLOAD_DIR + subFolder + File.separator;
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String original = file.getOriginalFilename();
        String ext = original.substring(original.lastIndexOf("."));
        String newName = System.currentTimeMillis() + ext;
        file.transferTo(Paths.get(uploadPath, newName).toFile());
        return newName;
    }

    public static void deleteFile(String fileName, String subFolder) {
        String filePath = UPLOAD_DIR + subFolder + File.separator + fileName;
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
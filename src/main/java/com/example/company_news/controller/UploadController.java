package com.example.company_news.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UploadController {

    private static final String UPLOAD_DIR =
            System.getProperty("user.dir") + "/uploads/";

    @PostMapping("/upload")
    public Map<String, String> upload(@RequestParam("file") MultipartFile file) throws Exception {
        if(file.isEmpty()){
            throw new RuntimeException("File rỗng");
        }
        String fileName = UUID.randomUUID() + "_" +
                file.getOriginalFilename().replaceAll("\\s+", "_");
        File folder = new File(UPLOAD_DIR);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File dest = new File(folder, fileName);
        file.transferTo(dest);
        Map<String, String> result = new HashMap<>();
        result.put("fileName", fileName);
        return result;
    }
}
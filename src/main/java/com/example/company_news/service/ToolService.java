package com.example.company_news.service;

import com.example.company_news.model.Tool;
import com.example.company_news.model.dto.tool.ToolRequest;
import com.example.company_news.model.dto.tool.ToolResponse;
import com.example.company_news.repository.ToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToolService extends BaseService {

    private final ToolRepository repository;

    public List<ToolResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(tool -> {
                    ToolResponse res = dto(tool, ToolResponse.class);
                    res.setCategory(tool.getCategory().getName());
                    return res;
                })
                .toList();
    }

    public List<ToolResponse> getByCategory(String id) {

        return repository.findByCategoryId(id)
                .stream()
                .map(tool -> {
                    ToolResponse res = dto(tool, ToolResponse.class);
                    res.setCategory(tool.getCategory().getName());
                    return res;
                })
                .toList();
    }

//    public List<ToolResponse> search(ToolRequest request) {
//
//        List<Tool> tools = repository.findAll();
//
//        return tools.stream()
//                .filter(t -> request.getName() == null ||
//                        t.getName().toLowerCase().contains(request.getName().toLowerCase()))
//                .filter(t -> request.getCategory() == null ||
//                        t.getCategory().getName().equalsIgnoreCase(request.getCategory()))
//                .map(tool -> {
//                    ToolResponse res = dto(tool, ToolResponse.class);
//                    res.setCategory(tool.getCategory().getName());
//                    return res;
//                })
//                .toList();
//    }
//
//    public Tool getById(String id){
//        return repository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Tool không tồn tại"));
//    }

    public ResponseEntity<Resource> download(String id) throws IOException {

        Tool tool = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tool không tồn tại"));

        Path path = Paths.get("uploads").resolve(tool.getDownloadLink());
        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        String filename = resource.getFilename();
        String encodedFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename*=UTF-8''" + encodedFilename)
                .body(resource);
    }

}

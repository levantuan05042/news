package com.example.company_news.service;

import com.example.company_news.model.InnovationPioneer;
import com.example.company_news.model.dto.innovationpioneer.InnovationPioneerDTO;
import com.example.company_news.model.dto.innovationpioneer.InnovationPioneerDetailDTO;
import com.example.company_news.repository.InnovationPioneerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InnovationPioneerService {
    private final InnovationPioneerRepository repository;

    public List<InnovationPioneerDTO> getAll() {
        return repository.findAllByOrderByCreatedAtDesc().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Value("${app.upload.dir}")
    private String uploadDir;

    public InnovationPioneerDTO convertToDTO(InnovationPioneer entity) {
        InnovationPioneerDTO dto = new InnovationPioneerDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setImage(entity.getImage() != null ? "/uploads/" + entity.getImage() : "default-pioneer.jpg");
        dto.setContent(entity.getContent());
        dto.setUnitName(entity.getUnitName());
        if (entity.getCreatedAt() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'Ngày' dd 'tháng' MM 'năm' yyyy", new Locale("vi", "VN"));
            dto.setCreatedAt(entity.getCreatedAt().format(formatter));
        }
        return dto;
    }

    public InnovationPioneerDetailDTO getDetail(String id) {
        InnovationPioneer entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin tấm gương"));

        return convertToDetailDTO(entity);
    }

    private InnovationPioneerDetailDTO convertToDetailDTO(InnovationPioneer entity) {
        InnovationPioneerDetailDTO dto = new InnovationPioneerDetailDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setImage(entity.getImage() != null ? "/uploads/" + entity.getImage() : "default-pioneer.jpg");
        dto.setUnitName(entity.getUnitName());
        dto.setContent(entity.getContent()); // Lấy toàn bộ nội dung
        dto.setFile(entity.getFile() != null ? "/uploads/" + entity.getFile() : null);

        if (entity.getFile() != null && entity.getFile().contains(".")) {
            String extension = entity.getFile().substring(entity.getFile().lastIndexOf(".") + 1).toLowerCase();
            dto.setFileType(determineFileType(extension));
        } else {
            dto.setFileType("none");
        }

        if (entity.getCreatedAt() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'Ngày' dd 'tháng' MM 'năm' yyyy", new Locale("vi", "VN"));
            dto.setCreatedAt(entity.getCreatedAt().format(formatter));
        }
        return dto;
    }

    private String determineFileType(String ext) {
        return switch (ext) {
            case "jpg", "jpeg", "png", "gif" -> "image";
            case "pdf" -> "pdf";
            case "doc", "docx" -> "word";
            case "xls", "xlsx" -> "excel";
            case "ppt", "pptx" -> "powerpoint";
            case "txt" -> "text";
            default -> "other";
        };
    }
}

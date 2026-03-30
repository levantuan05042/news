package com.example.company_news.service;

import com.example.company_news.model.CategoryInnovationIdea;
import com.example.company_news.model.InnovationIdea;
import com.example.company_news.model.dto.categoryinnovationidea.CategoryWithIdeasDTO;
import com.example.company_news.model.dto.innovationidea.InnovationIdeaDTO;
import com.example.company_news.model.dto.innovationidea.InnovationIdeaDetailDTO;
import com.example.company_news.repository.CategoryInnovationIdeaRepository;
import com.example.company_news.repository.InnovationIdeaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InnovationIdeaService {

    private final InnovationIdeaRepository ideaRepo;
    private final CategoryInnovationIdeaRepository categoryInnovationIdeaRepo;

    public List<InnovationIdeaDTO> getIdeasByCategory(String categoryId) {
        return ideaRepo.findByCategoryId(categoryId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private InnovationIdeaDTO mapToDTO(InnovationIdea idea) {
        return new InnovationIdeaDTO(
                idea.getId(),
                idea.getTitle(),
                idea.getContent(),
                idea.getImage() != null ? "/uploads/" + idea.getImage() : "default-idea.jpg",
                idea.getCreatedAt(),
                idea.getCategory() != null ? idea.getCategory().getId() : null
        );
    }

    public InnovationIdeaDetailDTO getDetailDto(String id) {
        InnovationIdea idea = ideaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy ý tưởng ID: " + id));

        InnovationIdeaDetailDTO dto = new InnovationIdeaDetailDTO();
        dto.setId(idea.getId());
        dto.setImage(idea.getImage() != null ? "/uploads/" + idea.getImage() : "default-idea.jpg");
        dto.setTitle(idea.getTitle());
        dto.setContent(idea.getContent());
        dto.setFullContent(idea.getFullContent());
        if (idea.getUser() != null) {
            dto.setAuthorName(idea.getUser().getFullname());
        } else {
            dto.setAuthorName("Tác giả ẩn danh");
        }

        if (idea.getCategory() != null) {
            dto.setCategoryName(idea.getCategory().getName());
        }

        if (idea.getCreatedAt() != null) {
            dto.setCreatedAt(idea.getCreatedAt().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        } else {
            dto.setCreatedAt("Vừa xong");
        }
        return dto;
    }
    public List<CategoryWithIdeasDTO> getAllIdeasGroupedByCategory() {
        List<CategoryInnovationIdea> categories = categoryInnovationIdeaRepo.findAll();
        List<InnovationIdea> allIdeas = ideaRepo.findAll();
        Map<String, List<InnovationIdeaDTO>> ideasByCatMap = allIdeas.stream()
                .map(this::mapToDTO) // Hàm mapToDTO mình đã viết ở trên
                .collect(Collectors.groupingBy(
                        dto -> dto.getCategoryId() != null ? dto.getCategoryId() : "other"
                ));
        return categories.stream()
                .map(cat -> new CategoryWithIdeasDTO(
                        cat.getId(),
                        cat.getName(),
                        ideasByCatMap.getOrDefault(cat.getId(), List.of())
                ))
                .filter(res -> !res.getIdeas().isEmpty()) // Chỉ hiện danh mục có ý tưởng
                .collect(Collectors.toList());
    }
}
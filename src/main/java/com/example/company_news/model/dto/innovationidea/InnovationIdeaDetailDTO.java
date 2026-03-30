package com.example.company_news.model.dto.innovationidea;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InnovationIdeaDetailDTO {
    private String id;
    private String image;
    private String title;
    private String content;      // Mô tả ngắn
    private String fullContent;  // Nội dung chi tiết (giữ \n hoặc HTML)
    private String createdAt;    // Đã convert sang String cho FE dễ đọc

    // Chỉ lấy các thông tin cần thiết từ User và Category
    private String authorName;
    private String categoryName;
}
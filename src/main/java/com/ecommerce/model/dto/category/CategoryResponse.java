package com.ecommerce.model.dto.category;

import com.ecommerce.model.dto.subcategory.SubCategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer subCategoryCount;
    private List<SubCategoryResponse> subCategories;
}

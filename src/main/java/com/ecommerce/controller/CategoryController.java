package com.ecommerce.controller;

import com.ecommerce.model.dto.GlobalResponse;
import com.ecommerce.model.dto.category.CategoryCreate;
import com.ecommerce.model.dto.category.CategoryResponse;
import com.ecommerce.service.category.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> findAllCategoryApi() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryResponse findCategoryApi(@PathVariable Long id) {
        return categoryService.findCategory(id);
    }

    @PostMapping
    public CategoryResponse createCategoryApi(@Valid @RequestBody CategoryCreate categoryCreate) {
        return categoryService.createCategory(categoryCreate);
    }

    @PutMapping("/{id}")
    public CategoryResponse updateCategoryApi(@PathVariable Long id,
                                           @Valid @RequestBody CategoryCreate categoryCreate) {
        return categoryService.updateCategory(id, categoryCreate);
    }

    @DeleteMapping("/{id}")
    public GlobalResponse deleteCategoryApi(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new GlobalResponse(200, "Category deleted successfully");
    }
}

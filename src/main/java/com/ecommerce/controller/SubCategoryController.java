package com.ecommerce.controller;


import com.ecommerce.model.dto.GlobalResponse;
import com.ecommerce.model.dto.subcategory.SubCategoryCreate;
import com.ecommerce.model.dto.subcategory.SubCategoryResponse;
import com.ecommerce.service.subcategory.SubCategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subcategories")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping
    public List<SubCategoryResponse> findAllSubCategoryApi() {
        return subCategoryService.findAllSubCategories();
    }

    @GetMapping("/{id}")
    public SubCategoryResponse findSubCategoryApi(@PathVariable Long id) {
        return subCategoryService.findSubCategory(id);
    }

    @PostMapping
    public SubCategoryResponse createCategoryApi(@Valid @RequestBody SubCategoryCreate subcategoryCreate) {
        return subCategoryService.createSubCategory(subcategoryCreate);
    }

    @PutMapping("/{id}")
    public SubCategoryResponse updateCategory(@PathVariable Long id,
                                           @Valid @RequestBody SubCategoryCreate subCategoryCreate) {
        return subCategoryService.updateSubCategory(id, subCategoryCreate);
    }

    @DeleteMapping("/{id}")
    public GlobalResponse deleteCategory(@PathVariable Long id) {
        return subCategoryService.deleteSubCategory(id);
    }

}

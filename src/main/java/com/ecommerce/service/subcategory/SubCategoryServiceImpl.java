package com.ecommerce.service.subcategory;

import com.ecommerce.error.exception.DuplicateResourceException;
import com.ecommerce.error.exception.ResourceNotFoundException;
import com.ecommerce.model.dto.GlobalResponse;
import com.ecommerce.model.dto.subcategory.SubCategoryCreate;
import com.ecommerce.model.dto.subcategory.SubCategoryResponse;
import com.ecommerce.model.entity.Category;
import com.ecommerce.model.entity.SubCategory;
import com.ecommerce.repository.SubCategoryRepository;
import com.ecommerce.service.category.CategoryService;
import com.ecommerce.util.subcategory.SubCategoryTransformation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ecommerce.util.subcategory.SubCategoryTransformation.toSubCategory;
import static com.ecommerce.util.subcategory.SubCategoryTransformation.toSubCategoryResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryService categoryService;

    @Override
    @Cacheable(value= "subcategories", key = "#id")
    public SubCategoryResponse findSubCategory(@NotNull final Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id).orElseThrow(() -> {
//            log.error("Category with id {} not found", id);
            return new ResourceNotFoundException("subCategory not found",
                    "No subcategory found with the provided ID: " + id);
        });
        return toSubCategoryResponse(subCategory);
    }

    @Override
    @Cacheable(value = "subcategories", key = "#root.methodName")
    public List<SubCategoryResponse> findAllSubCategories() {
        return subCategoryRepository.findAll()
                .stream()
                .map(SubCategoryTransformation::toSubCategoryResponse)
                .toList();
    }

    @Override
    @CacheEvict(value = "subcategories", allEntries = true)
    public SubCategoryResponse createSubCategory(@NotNull final SubCategoryCreate subCategoryCreate) {
        if (existsByName(subCategoryCreate.getName())) {
            throw new DuplicateResourceException("subCategory with name '" + subCategoryCreate.getName() + "' already exists.",
                    "A subcategory with the same name already exists in the database.");
        }

        Category category = categoryService.findCategoryEntity(subCategoryCreate.getCategoryId());

        SubCategory subCategory =  subCategoryRepository.save(toSubCategory(subCategoryCreate, category));
        return toSubCategoryResponse(subCategory);
    }

    @Override
    @CacheEvict(value = "subcategories", allEntries = true)
    public SubCategoryResponse updateSubCategory(@NotNull final Long id, @NotNull final SubCategoryCreate subCategoryCreate) {
        SubCategory subCategory = subCategoryRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("subCategory not found",
                    "No subcategory found with the provided ID: " + id);
        });

        if (existsByName(subCategoryCreate.getName()) && !subCategory.getName().equals(subCategoryCreate.getName())) {
            throw new DuplicateResourceException("subCategory with name '" + subCategoryCreate.getName() + "' already exists.",
                    "A subcategory with the same name already exists in the database.");
        }

        Category category = categoryService.findCategoryEntity(subCategoryCreate.getCategoryId());

        subCategory.setName(subCategoryCreate.getName());
        subCategory.setDescription(subCategoryCreate.getDescription());
        subCategory.setCategory(category);

        SubCategory updatedSubCategory = subCategoryRepository.save(subCategory);
        return toSubCategoryResponse(updatedSubCategory);

    }

    @Override
    @CacheEvict(value = "subcategories", allEntries = true)
    public GlobalResponse deleteSubCategory(@NotNull final Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("SubCategory not found",
                    "No subcategory found with the provided ID: " + id);
        });

        subCategoryRepository.delete(subCategory);
        return new GlobalResponse(200, "SubCategory deleted successfully");
    }

    private boolean existsByName(String name) {
        return subCategoryRepository.existsByName(name);
    }
}

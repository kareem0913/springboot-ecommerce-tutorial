package com.ecommerce.service.category;

import com.ecommerce.error.exception.DuplicateResourceException;
import com.ecommerce.error.exception.ResourceNotFoundException;
import com.ecommerce.model.dto.category.CategoryCreate;
import com.ecommerce.model.dto.category.CategoryResponse;
import com.ecommerce.model.entity.Category;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.util.category.CategoryTransformation;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ecommerce.util.category.CategoryTransformation.toCategory;
import static com.ecommerce.util.category.CategoryTransformation.toCategoryResponse;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final MessageSource messageSource;

    @Override
    @Cacheable(value = "categories", key = "#id")
    public CategoryResponse findCategory(@NotNull final Long id) {
       Category category = categoryRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Category not found",
                    "No category found with the provided ID: " + id);
       });
        return toCategoryResponse(category);
    }

    @Override
    @Cacheable(value = "categories", key = "#root.methodName.concat(#id)")
    public Category findCategoryEntity(@NotNull final Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Category not found",
                    "No category found with the provided ID: " + id);
        });
    }

    @Override
    @Cacheable(value = "categories", key = "'allCategories'")
    public List<CategoryResponse> findAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryTransformation::toCategoryResponse)
                .toList();
    }

    @Override
    @CacheEvict(value = "categories", allEntries = true)
    public CategoryResponse createCategory(@NotNull final CategoryCreate categoryCreate) {
        if (existsByName(categoryCreate.getName())) {
            Object[] args = {categoryCreate.getName()};
            String msg = messageSource.getMessage("validation.category.duplicate.name", args, LocaleContextHolder.getLocale());
            String description = messageSource.getMessage("validation.category.duplicate.name.description", null, LocaleContextHolder.getLocale());
            throw new DuplicateResourceException(msg, description);
        }

       Category category =  categoryRepository.save(toCategory(categoryCreate));
        return toCategoryResponse(category);
    }

    @Override
    @CacheEvict(value = "categories", allEntries = true)
    public CategoryResponse updateCategory(@NotNull final Long id, @NotNull final CategoryCreate categoryCreate) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> {
            log.error("Category with id {} not found", id);
            return new ResourceNotFoundException("Category not found",
                    "No category found with the provided ID: " + id);
        });

        if (existsByName(categoryCreate.getName()) && !category.getName().equals(categoryCreate.getName())) {
            log.error("Category with name {} already exists", categoryCreate.getName());
            throw new DuplicateResourceException("Category with name '" + categoryCreate.getName() + "' already exists.",
                    "A category with the same name already exists in the database.");
        }

        category.setName(categoryCreate.getName());
        category.setDescription(categoryCreate.getDescription());

        Category updatedCategory = categoryRepository.save(category);
        return toCategoryResponse(updatedCategory);
    }

    @Override
    @CacheEvict(value = "categories", allEntries = true)
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> {
            log.error("Category with id {} not found", id);
            return new ResourceNotFoundException("Category not found",
                    "No category found with the provided ID: " + id);
        });
        categoryRepository.delete(category);
    }

    private boolean existsByName(@NotNull final String name) {
        return categoryRepository.existsByName(name);
    }

}

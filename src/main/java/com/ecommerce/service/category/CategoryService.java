package com.ecommerce.service.category;

import com.ecommerce.model.dto.category.CategoryCreate;
import com.ecommerce.model.dto.category.CategoryResponse;
import com.ecommerce.model.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryResponse findCategory(Long id);

    Category findCategoryEntity(Long id);

    List<CategoryResponse> findAllCategories();

    CategoryResponse createCategory(CategoryCreate categoryCreate);

    CategoryResponse updateCategory(Long id, CategoryCreate categoryCreate);

    void deleteCategory(Long id);

}

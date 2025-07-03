package com.ecommerce.mapper;

import com.ecommerce.model.dto.category.CategoryResponse;
import com.ecommerce.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SubCategoryMapper.class})
public interface CategoryMapper {

    CategoryResponse toCategoryResponse(Category category);

    Category toCategory(CategoryResponse categoryResponse);
}

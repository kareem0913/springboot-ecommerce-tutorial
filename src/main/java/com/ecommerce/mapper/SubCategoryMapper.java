package com.ecommerce.mapper;


import com.ecommerce.model.dto.subcategory.SubCategoryResponse;
import com.ecommerce.model.entity.SubCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubCategoryMapper {

    @Mapping(target = "categoryId" , source = "category.id")
    SubCategoryResponse toSubCategoryResponse(SubCategory subCategory);

    SubCategory toSubCategory(SubCategoryResponse subCategoryResponse);

}

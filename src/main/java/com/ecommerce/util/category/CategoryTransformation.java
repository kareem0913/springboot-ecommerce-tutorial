package com.ecommerce.util.category;

import com.ecommerce.model.dto.category.CategoryCreate;
import com.ecommerce.model.dto.category.CategoryResponse;
import com.ecommerce.model.dto.subcategory.SubCategoryResponse;
import com.ecommerce.model.entity.Category;
import com.ecommerce.util.subcategory.SubCategoryTransformation;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CategoryTransformation {
   public static CategoryResponse toCategoryResponse(@NotNull final Category category){
       List<SubCategoryResponse> subCategories = category.getSubCategories().stream()
               .map(SubCategoryTransformation::toSubCategoryResponse)
               .toList();

       return new CategoryResponse(
               category.getId(),
               category.getName(),
               category.getDescription(),
               category.getCreatedAt(),
               category.getUpdatedAt(),
               category.getSubCategoryCount(),
               subCategories
       );
   }

   public static Category toCategory(@NotNull final CategoryCreate categoryCreate){
       return new Category(
               categoryCreate.getName(),
               categoryCreate.getDescription()
       );
   }

    public static Category toCategory(@NotNull final CategoryResponse categoryResponse){
        return new Category(
                categoryResponse.getName(),
                categoryResponse.getDescription()
        );
    }
}

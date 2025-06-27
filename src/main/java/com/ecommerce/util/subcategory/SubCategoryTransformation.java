package com.ecommerce.util.subcategory;

import com.ecommerce.model.dto.subcategory.SubCategoryCreate;
import com.ecommerce.model.dto.subcategory.SubCategoryResponse;
import com.ecommerce.model.entity.Category;
import com.ecommerce.model.entity.SubCategory;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SubCategoryTransformation {
    public static SubCategoryResponse toSubCategoryResponse(final SubCategory subCategory) {
        return new SubCategoryResponse(
                subCategory.getId(),
                subCategory.getName(),
                subCategory.getDescription(),
                subCategory.getCreatedAt(),
                subCategory.getUpdatedAt(),
                subCategory.getCategory().getId()
        );
    }

    public static SubCategory toSubCategory(final SubCategoryCreate subCategoryCreate, final Category category) {
        return new SubCategory(
                subCategoryCreate.getName(),
                subCategoryCreate.getDescription(),
                category
        );
    }
}

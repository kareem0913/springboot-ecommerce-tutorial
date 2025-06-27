package com.ecommerce.service.subcategory;

import com.ecommerce.model.dto.GlobalResponse;
import com.ecommerce.model.dto.subcategory.SubCategoryCreate;
import com.ecommerce.model.dto.subcategory.SubCategoryResponse;

import java.util.List;

public interface SubCategoryService {

    SubCategoryResponse findSubCategory(Long id);

    List<SubCategoryResponse> findAllSubCategories();

    SubCategoryResponse createSubCategory(SubCategoryCreate subCategoryCreate);

    SubCategoryResponse updateSubCategory(Long id, SubCategoryCreate subCategoryCreate);

    GlobalResponse deleteSubCategory(Long id);

}

package com.etiya.ecommercedemopair1.business.abstracts;

import com.etiya.ecommercedemopair1.business.dtos.request.category.AddCategoryRequest;
import com.etiya.ecommercedemopair1.business.dtos.response.category.GetCategoryResponse;
import com.etiya.ecommercedemopair1.core.util.results.DataResult;
import com.etiya.ecommercedemopair1.entities.concretes.Category;

import java.util.List;

public interface CategoryService {
    DataResult<List<Category>> getAll();
    DataResult<Category> getById(int id);
    DataResult<List<Category>> findAllByName(String name);
    DataResult<List<Category>> getCategoryWithIdDesc();
    DataResult<GetCategoryResponse> addCategory(AddCategoryRequest addCategoryRequest);

    DataResult<GetCategoryResponse> getCategoryResponse(AddCategoryRequest addCategoryRequest);

}

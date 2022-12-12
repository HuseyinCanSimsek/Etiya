package com.etiya.ecommercedemopair1.business.concretes;

import com.etiya.ecommercedemopair1.business.abstracts.CategoryService;
import com.etiya.ecommercedemopair1.business.constants.Messages;
import com.etiya.ecommercedemopair1.business.dtos.request.category.AddCategoryRequest;
import com.etiya.ecommercedemopair1.business.dtos.response.category.GetCategoryResponse;
import com.etiya.ecommercedemopair1.core.util.exceptions.BusinessException;
import com.etiya.ecommercedemopair1.core.util.mapping.ModelMapperService;
import com.etiya.ecommercedemopair1.core.util.results.DataResult;
import com.etiya.ecommercedemopair1.core.util.results.SuccessDataResult;
import com.etiya.ecommercedemopair1.entities.concretes.Category;
import com.etiya.ecommercedemopair1.repository.abstracts.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {

    private CategoryRepository categoryRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public DataResult<List<Category>> getAll() {

        List<Category> categories=this.categoryRepository.findAll();
        return new SuccessDataResult<List<Category>>("All categories were called",categories);
    }

    @Override
    public DataResult<Category> getById(int id) {

        Category category= this.categoryRepository.findById(id).orElseThrow();
       return new SuccessDataResult<Category>("Category was found by given id",category);
    }

    @Override
    public DataResult<List<Category>> findAllByName(String name) {

        List<Category> categories=this.categoryRepository.findAllByName(name);
        checkCategoryByName(categories);

        return new SuccessDataResult<List<Category>>("Categories was found by given name",categories);
    }

    private  void checkCategoryByName(List<Category> categories) {
        if(categories.size()==0)
        {
            throw new BusinessException("Categories were not found according to name");
        }
    }

    @Override
    public DataResult<List<Category>> getCategoryWithIdDesc() {
        List<Category> categoriesDesc=this.categoryRepository.getCategoryWithIdDesc();
        return new SuccessDataResult<>("Categories was sorted by themselves id",categoriesDesc);
    }


    public DataResult<GetCategoryResponse> addCategory(AddCategoryRequest addCategoryRequest) {
        // MappingCategory
        //  Mapping: AUTO MAPPER
     /*  --->Manuel mapping
      Category category = new Category();
      category.setName(addCategoryRequest.getName());
      */

        Category category = modelMapperService.getMapperforRequest().map(addCategoryRequest,Category.class);
        checkCategoryNameExists(category);
        Category savedCategory = this.categoryRepository.save(category);



        //Mapping => Category => AddCategoryResponse
        /*GetCategoryResponse response =
                new GetCategoryResponse(savedCategory.getId(), savedCategory.getName());
        */
        GetCategoryResponse response = modelMapperService.getMapperforResponse().map(savedCategory, GetCategoryResponse.class);
        return new SuccessDataResult<GetCategoryResponse>("Category was added successfully",response);

    }

    @Override
    public DataResult<GetCategoryResponse> getCategoryResponse(AddCategoryRequest addCategoryRequest) {

        Category category = modelMapperService.getMapperforRequest().map(addCategoryRequest,Category.class);

        checkCategoryNameExists(category);
        Category savedCategory = this.categoryRepository.save(category);

        GetCategoryResponse getCategoryResponse = modelMapperService.getMapperforResponse().map(savedCategory, GetCategoryResponse.class);
        return new SuccessDataResult<GetCategoryResponse>("Category was added succesfully",getCategoryResponse);

    }

    private void checkCategoryNameExists(Category category) {
        List<Category> categories = categoryRepository.findAllByName(category.getName());
        if(categories.size()==0)
        {
            throw new BusinessException("Categories were not found given name");
        }

    }
}
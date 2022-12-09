package com.etiya.ecommercedemopair1.business.abstracts;

public interface ProductCategoryService {
    boolean existsByCategoryId(int id);
    boolean existsByProductId(int id);
}

package com.etiya.ecommercedemopair1.business.concretes;

import com.etiya.ecommercedemopair1.business.abstracts.ProductCategoryService;
import com.etiya.ecommercedemopair1.repository.abstracts.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductCategoryManager implements ProductCategoryService {
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public boolean existsByCategoryId(int id) {
        return productCategoryRepository.existsByCategoryId(id);
    }

    @Override
    public boolean existsByProductId(int id) {
        return productCategoryRepository.existsByProductId(id);
    }
}

package com.etiya.ecommercedemopair1.business.abstracts;

import com.etiya.ecommercedemopair1.business.dtos.request.product.AddProductRequest;
import com.etiya.ecommercedemopair1.business.dtos.response.product.GetProductResponse;
import com.etiya.ecommercedemopair1.core.util.results.DataResult;
import com.etiya.ecommercedemopair1.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    DataResult<List<Product>> getAll();
    DataResult<Product> getById(int id);

    DataResult<List<Product>> findAllProductsByStockGreaterThanOrderByStockAsc(int stock);

    DataResult<List<Product>> findAllByOrderByNameAsc();
    DataResult<Product> getByName(String name);
    DataResult<String> getProductNameWithId(int id);

    DataResult<GetProductResponse> addProduct(AddProductRequest addProductRequest);
    DataResult<List<GetProductResponse>> getProductCategories(int id);

}

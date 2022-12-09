package com.etiya.ecommercedemopair1.business.concretes;

import com.etiya.ecommercedemopair1.business.abstracts.ProductCategoryService;
import com.etiya.ecommercedemopair1.business.abstracts.ProductService;
import com.etiya.ecommercedemopair1.business.constants.Messages;
import com.etiya.ecommercedemopair1.business.dtos.request.product.AddProductRequest;
import com.etiya.ecommercedemopair1.business.dtos.response.product.GetProductResponse;
import com.etiya.ecommercedemopair1.core.util.mapping.ModelMapperService;
import com.etiya.ecommercedemopair1.entities.concretes.Category;
import com.etiya.ecommercedemopair1.entities.concretes.Product;
import com.etiya.ecommercedemopair1.repository.abstracts.CategoryRepository;
import com.etiya.ecommercedemopair1.repository.abstracts.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private  ModelMapperService modelMapperService;




    public ProductManager() {
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(int id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> findAllProductsByStockGreaterThanOrderByStockAsc(int stock) {
        return productRepository.findAllProductsByStockGreaterThanOrderByStockAsc(stock);
    }

    @Override
    public List<Product> findAllByOrderByNameAsc() {
        return productRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Product getByName(String name) {
        return productRepository.findByName(name);
    }
    @Override
    public String getProductNameWithId(int id) {
        return productRepository.getProductNameWithId(id);
    }

    @Override
    public GetProductResponse addProduct(AddProductRequest addProductRequest) {

        // Mapping
        Product product = modelMapperService.getMapperforRequest().map(addProductRequest,Product.class);

        Category category = new Category();


        checkCategoryWithId(addProductRequest.getCategoryId());
        category.setId(addProductRequest.getCategoryId());

        Product savedProduct = productRepository.save(product);


        GetProductResponse getProductResponse = modelMapperService.getMapperforResponse().map(savedProduct,GetProductResponse.class);

        return getProductResponse;
    }

    @Override
    public List<GetProductResponse> getProductCategories(int id) {
        checkCategoryId(id);
        return productRepository.getProductCategories(id);
    }

    public void checkCategoryId(int id)
    {
        boolean isExists = productCategoryService.existsByCategoryId(id);
        if (!isExists) {
            throw new RuntimeException(Messages.ProductCategory.categoryExistsInProduct);
        }
    }
    public void checkCategoryWithId(int id) {
        boolean isExists = categoryRepository.existsById(id);
        if (!isExists) {
            throw new RuntimeException(Messages.Category.categoryExistsInProduct);
        }
    }

}

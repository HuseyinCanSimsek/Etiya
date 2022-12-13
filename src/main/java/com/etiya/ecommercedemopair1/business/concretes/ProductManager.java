package com.etiya.ecommercedemopair1.business.concretes;

import com.etiya.ecommercedemopair1.business.abstracts.ProductCategoryService;
import com.etiya.ecommercedemopair1.business.abstracts.ProductService;
import com.etiya.ecommercedemopair1.business.constants.Messages;
import com.etiya.ecommercedemopair1.business.dtos.request.product.AddProductRequest;
import com.etiya.ecommercedemopair1.business.dtos.response.product.GetProductResponse;
import com.etiya.ecommercedemopair1.core.util.mapping.ModelMapperService;
import com.etiya.ecommercedemopair1.core.util.results.DataResult;
import com.etiya.ecommercedemopair1.core.util.results.SuccessDataResult;
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
    public DataResult<List<Product>> getAll() {
        List<Product> products=productRepository.findAll();
        return new SuccessDataResult<List<Product>>("All products were called",products);
    }

    @Override
    public DataResult<Product> getById(int id) {

        Product product= productRepository.findById(id).orElseThrow();
        return new SuccessDataResult<>("Product was found",product);
    }

    @Override
    public DataResult<List<Product>> findAllProductsByStockGreaterThanOrderByStockAsc(int stock) {
        List<Product> products=productRepository.findAllProductsByStockGreaterThanOrderByStockAsc(stock);
        return new SuccessDataResult<List<Product>>("Products were listed greater than given stock",products);
    }

    @Override
    public DataResult<List<Product>> findAllByOrderByNameAsc() {

        List<Product> products=productRepository.findAllByOrderByNameAsc();
        return new SuccessDataResult<List<Product>>("Products were sorted ascending",products);
    }

    @Override
    public DataResult<Product> getByName(String name) {
        Product product= productRepository.findByName(name);
        return  new SuccessDataResult<Product>("Product was found given name from user",product);
    }
    @Override
    public DataResult<String> getProductNameWithId(int id) {

        String productName= productRepository.getProductNameWithId(id);
        return new SuccessDataResult<>("Product name was found given name from user",productName);
    }

    @Override
    public DataResult<GetProductResponse> addProduct(AddProductRequest addProductRequest) {

        // Mapping
        Product product = modelMapperService.getMapperforRequest().map(addProductRequest,Product.class);

        Category category = new Category();


        checkCategoryWithId(addProductRequest.getCategoryId());
        category.setId(addProductRequest.getCategoryId());

        Product savedProduct = productRepository.save(product);


        GetProductResponse getProductResponse = modelMapperService.getMapperforResponse().map(savedProduct,GetProductResponse.class);

        return new SuccessDataResult<>("Product was added succesfully",getProductResponse);
    }

    @Override
    public DataResult<List<GetProductResponse>> getProductCategories(int id) {
        checkCategoryId(id);
        List<GetProductResponse> getProductResponses=productRepository.getProductCategories(id);
        return new SuccessDataResult<>("Products was found according to category id");
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

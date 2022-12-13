package com.etiya.ecommercedemopair1.api.controllers;


import com.etiya.ecommercedemopair1.business.abstracts.ProductService;

import com.etiya.ecommercedemopair1.business.constants.Paths;
import com.etiya.ecommercedemopair1.business.dtos.request.product.AddProductRequest;
import com.etiya.ecommercedemopair1.business.dtos.response.product.GetProductResponse;
import com.etiya.ecommercedemopair1.core.util.results.DataResult;
import com.etiya.ecommercedemopair1.entities.concretes.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix+"products")
public class ProductsController {
    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/getAll")
    public DataResult<List<Product>> getAll() {
        return productService.getAll();
    }

    @GetMapping("/getById/{id}")
    public DataResult<Product> getById(@PathVariable int id) {
        return productService.getById(id);
    }

    @GetMapping("/getAllProductsByStockGreaterThan")
    public DataResult<List<Product>> findAllProductsByStockGreaterThanOrderByStockAsc(@RequestParam int stock) {

        return productService.findAllProductsByStockGreaterThanOrderByStockAsc(stock);
    }
    @GetMapping("/getByName")
    public DataResult<Product> getByName(@RequestParam("name") String name) {
        return productService.getByName(name);
    }

    @GetMapping("/getAlphabeticProduct")
    public DataResult<List<Product>> findAllByOrderByNameAsc() {
        return this.productService.findAllByOrderByNameAsc();
    }

    @GetMapping("/getProductNameWithId/{id}")
    public DataResult<String> getProductNameWithId(@PathVariable int id) {
        return this.productService.getProductNameWithId(id);
    }

    @PostMapping("/add")
    public DataResult<GetProductResponse> addProduct(@RequestBody @Valid AddProductRequest addProductRequest) {
        return productService.addProduct(addProductRequest);
    }

    @PostMapping("/adddone")
    public ResponseEntity<DataResult<GetProductResponse>> addProductOne(@RequestBody AddProductRequest addProductRequest) {
        return new ResponseEntity<DataResult<GetProductResponse>>(productService.addProduct(addProductRequest), HttpStatus.CREATED);
    }
    @GetMapping("/getProductCategories")
    public @ResponseBody DataResult<List<GetProductResponse>> getProductCategories(int id) {
        return productService.getProductCategories(id);
    }



}

package com.etiya.ecommercedemopair1.repository.abstracts;

import com.etiya.ecommercedemopair1.business.dtos.response.product.GetProductResponse;
import com.etiya.ecommercedemopair1.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllProductsByStockGreaterThanOrderByStockAsc(int stock);
    List<Product> findAllByOrderByNameAsc();

    @Query("Select p.name from Product as p where p.id=:id")
    String getProductNameWithId(int id);

    @Query("Select p from Product as p WHERE name=:name")
    public Product findByName(String name);
    @Query("Select new com.etiya.ecommercedemopair1.business.dtos.response.product.GetProductResponse(p.name,p.stock,p.unitPrice,p.discountRate) from Product " +
            "as p inner join ProductCategory as pc on p=pc.product inner join Category as c on pc.category=c where c.id in(:identity)" +
            " group by p.name,p.stock,p.unitPrice,p.discountRate")
    List<GetProductResponse> getProductCategories(int identity);


}

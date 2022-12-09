package com.etiya.ecommercedemopair1.repository.abstracts;

import com.etiya.ecommercedemopair1.entities.concretes.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
   @Query("select case when count(c) > 0 then true else false end from ProductCategory as p inner join Category as c on p.category=c where c.id=:id")
    boolean existsByCategoryId(int id);
    @Query("select case when count(p) > 0 then true else false end from ProductCategory as pc inner join Product as p on pc.product=p where p.id=:id")
    boolean existsByProductId(int id);

}

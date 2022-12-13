package com.etiya.ecommercedemopair1.repository.abstracts;

import com.etiya.ecommercedemopair1.entities.concretes.Cart;
import com.etiya.ecommercedemopair1.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query(value = "select p from Product as p  join  " +
            "    ProductCart as pc on p.id=pc.product.id "+
            "      inner join Cart as c on pc.cart.id=c.id where c.id in(:identity)",nativeQuery = false)
    List<Product> getProductsWithCartId(int identity);
}

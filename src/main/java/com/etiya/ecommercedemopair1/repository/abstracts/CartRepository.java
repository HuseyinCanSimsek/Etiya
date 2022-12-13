package com.etiya.ecommercedemopair1.repository.abstracts;

import com.etiya.ecommercedemopair1.entities.concretes.Cart;
import com.etiya.ecommercedemopair1.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query(value = "select * from products as p inner join  " +
            "    product_cart as pc on p.id=pc.product_id "+
            "      inner join carts as c on pc.cart_id=c.id where c.id in(:identity)",nativeQuery = true)
    List<Product> getProductsWithCartId(int identity);
}

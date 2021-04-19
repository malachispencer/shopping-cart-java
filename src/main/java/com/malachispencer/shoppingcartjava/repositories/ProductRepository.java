package com.malachispencer.shoppingcartjava.repositories;

import com.malachispencer.shoppingcartjava.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}

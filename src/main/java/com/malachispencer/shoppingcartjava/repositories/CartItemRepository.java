package com.malachispencer.shoppingcartjava.repositories;

import com.malachispencer.shoppingcartjava.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}

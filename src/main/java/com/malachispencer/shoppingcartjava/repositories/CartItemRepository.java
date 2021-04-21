package com.malachispencer.shoppingcartjava.repositories;

import com.malachispencer.shoppingcartjava.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>, CartItemCustomRepository {
    boolean existsCartItemByProductID(Integer productID);

    CartItem findOneByProductID(Integer productID);

    @Transactional
    @Modifying
    @Query("UPDATE cart c SET c.quantity = :newQuantity WHERE productID = :productID")
    void updateCartItemQuantity(
        @Param("newQuantity") Integer newQuantity,
        @Param("productID") Integer productID
    );
}

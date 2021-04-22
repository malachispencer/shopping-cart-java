package com.malachispencer.shoppingcartjava.repositories;

import com.malachispencer.shoppingcartjava.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>, CartItemCustomRepository {
    boolean existsCartItemByProductID(Integer productID);

    CartItem findOneByProductID(Integer productID);

    @Transactional
    @Modifying
    @Query("UPDATE cart c " +
        "SET c.quantity = :newQuantity " +
        "WHERE productID = :productID"
    )
    void updateCartItemQuantity(
        @Param("newQuantity") Integer newQuantity,
        @Param("productID") Integer productID
    );

    @Query(
        "SELECT new map (" +
            "c.cartID as cartID, " +
            "p.productID as productID, " +
            "p.name as name, p.price as price, " +
            "c.quantity as quantity, " +
            "p.inStock as inStock" +
            ") " +
            "FROM products p INNER JOIN cart c " +
            "ON p.productID = c.productID"
    )
    List<Map> getItems();
}

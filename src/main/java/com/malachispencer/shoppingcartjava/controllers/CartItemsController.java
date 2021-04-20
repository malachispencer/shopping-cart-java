package com.malachispencer.shoppingcartjava.controllers;

import com.malachispencer.shoppingcartjava.models.CartItem;
import com.malachispencer.shoppingcartjava.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CartItemsController {
    @Autowired
    CartItemRepository cartItemRepository;

    @GetMapping("/cart")
    public String index(Model model) {
        List<CartItem> cartItems = cartItemRepository.findAll();

        for (CartItem cartItem : cartItems) {
            System.out.println(cartItem.getProduct().toString());
        }

        return "cart";
    }

//    @PostMapping("/cart")
//    public String add(@ModelAttribute("cart") Cart cart) {
//        System.out.println(cart);
//        return "products";
//    }

}

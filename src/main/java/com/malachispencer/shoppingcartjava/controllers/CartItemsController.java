package com.malachispencer.shoppingcartjava.controllers;

import com.malachispencer.shoppingcartjava.models.CartItem;
import com.malachispencer.shoppingcartjava.models.Product;
import com.malachispencer.shoppingcartjava.repositories.CartItemRepository;
import com.malachispencer.shoppingcartjava.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CartItemsController {
    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/cart")
    public String index(Model model) {
        Long itemsInCart = cartItemRepository.count();
        model.addAttribute("itemsInCart", itemsInCart);
        return "cart";
    }

    @PostMapping("/cart")
    public String addItem(@ModelAttribute("cartItem") CartItem cartItem) {
        Integer productID = cartItem.getProductID();
        Integer addingToCart = cartItem.getQuantity();
        Integer currentlyInStock = productRepository
            .getOne(productID)
            .getInStock();
        boolean exists = cartItemRepository
            .existsCartItemByProductID(productID);

        if (exists == false) {
            cartItemRepository.saveAndFlush(cartItem);
        } else {
            Integer currentQuantity = cartItemRepository.
                findOneByProductID(productID).
                getQuantity();
            Integer newQuantity = currentQuantity + addingToCart;
            cartItemRepository.updateCartItemQuantity(newQuantity, productID);
        }

        Integer newInStock = currentlyInStock - addingToCart;
        productRepository.decrementStock(newInStock, productID);

        return "redirect:/";
    }
}

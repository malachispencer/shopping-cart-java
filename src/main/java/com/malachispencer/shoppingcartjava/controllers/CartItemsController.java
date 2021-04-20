package com.malachispencer.shoppingcartjava.controllers;

import com.malachispencer.shoppingcartjava.models.CartItem;
import com.malachispencer.shoppingcartjava.models.Product;
import com.malachispencer.shoppingcartjava.repositories.CartItemRepository;
import com.malachispencer.shoppingcartjava.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "cart";
    }

    @PostMapping("/cart")
    public String add(@ModelAttribute("cartItem") CartItem cartItem, Model model) {
        System.out.println(cartItem.toString());

        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "products";
    }
}

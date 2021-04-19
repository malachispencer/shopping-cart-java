package com.malachispencer.shoppingcartjava.controllers;

import com.malachispencer.shoppingcartjava.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String products(Model model) {
        model.addAttribute(
            "products",
            productRepository.findAll(
                Sort.by(Sort.Direction.ASC, "productID")
            )
        );

        return "products";
    }
}

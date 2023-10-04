package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.ProductData;
import com.example.demo.models.ProductRequest;
import com.example.demo.models.ProductRequest.Product;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductRequestService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ProductController {

    // Get all Products
    @GetMapping("/products")
    public String viewHomePage(Model model, HttpServletResponse response) {
        List<Product> products = ProductRequestService.getAllProducts();
        model.addAttribute("allProductList", products);

        return "products";
    }

    // Add to Cart
    @PostMapping("/products")
    public String addToCart(@ModelAttribute("product") Product product) {
        CartService.addCartItem(product, "1");
        return "redirect:/products";
    }

    // Go to Detailpage
    @GetMapping("/products/{id}")
    public String viewProductDetail(@PathVariable String id, Model model, HttpServletResponse response) {
        model.addAttribute("product", ProductRequestService.getProductByID(id));
        return "productDetail";
    }

    // Add to Cart DetailPage
    @PostMapping("/products/{id}")
    public String addToCartPd(@PathVariable String id, Model model) {
        ProductData prodData = ProductRequestService.getProductByID(id);

        ProductRequest productRequest = new ProductRequest();
        ProductRequest.Product product = productRequest.new Product();
        product.id = prodData.getId();

        CartService.addCartItem(product, "1");
        return "redirect:/products/" + id;
    }

}

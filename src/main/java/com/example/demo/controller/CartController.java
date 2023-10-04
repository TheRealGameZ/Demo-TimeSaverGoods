package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;

@Controller
public class CartController {

    // View Cart
    @GetMapping("/cart")
    public String viewCart(Model model) {
        try {
            OrderService.cancelCheckout();
            CartService.getCart();

        } catch (Exception e) {
            CartService.createCart();
        }

        model.addAttribute("cartItems", CartService.getCartItems().getCartItems());
        model.addAttribute("cartTotal", CartService.getCart().getGrandTotalAmount());
        return "cart";
    }

    // Delete Cart
    @GetMapping("/cart/delete")
    public String delCart() {
        CartService.delCart();
        return "redirect:/";
    }

    // Update CartItem-Quantity
    @PostMapping("/updateCartItem")
    public String editCartItem(@RequestParam("id") String id, @RequestParam("quantity") String quantity) {
        CartService.editCartItem(id, quantity);
        return "redirect:/cart";
    }

    // Remove CartItem from Cart
    @GetMapping("/removeFromCart/{id}")
    public String removeCartItem(@PathVariable String id) {
        CartService.removeCartItems(id);
        return "redirect:/cart";
    }

}

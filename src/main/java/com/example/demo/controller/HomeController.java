package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.ProductRequest.ProductsPage.Products;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductRequestService;

import jakarta.servlet.http.HttpServletResponse;


@Controller
public class HomeController 
{
     //----------------------------- HOME -----------------------------//
     @GetMapping("/")
     public String index(Model model, HttpServletResponse response) 
     {
          model.addAttribute("allProductList", ProductRequestService.getSomeProducts());
          return "home";
     }
     

     @PostMapping("/")
     public String redirectToProductDetail(@ModelAttribute("product") Products product, @RequestParam("quantity") String quantity)
     {
          return "/products";
     }


     //----------------------------- PRODUCTS -----------------------------//

     //Get all Products
     @GetMapping("/products")
     public String viewHomePage(Model model, HttpServletResponse response) 
     {
          model.addAttribute("allProductList", ProductRequestService.getAllProducts());
          return "products";
     }

     //Add to Cart
     @PostMapping("/products")
     public String addToCart(@ModelAttribute("product") Products product, @RequestParam("quantity") String quantity)
     {
          CartService.addCartItem(product, quantity);
          return "redirect:/products";
     }

     //Go to Detailpage
     @GetMapping("/products/{id}")
     public String viewProductDetail(Model model, HttpServletResponse response) 
     {
          
          return "productDetail";
     }

     //----------------------------- ABOUT -----------------------------//


    @GetMapping("/about")
     public String returnAbout() 
     {
          return "about";
     }


     //----------------------------- CART -----------------------------//

     //View Cart
     @GetMapping("/cart")
     public String viewCart(Model model) 
     {
          try 
          {
               CartService.getCart();

          } catch (Exception e)
          {
               CartService.createCart();
          }
          
          model.addAttribute("cartItems", CartService.getCartItems().getCartItems());
          return "cart";
     }

     //Delete Cart
     @GetMapping("/cart/delete")
     public String delCart() 
     {
          CartService.delCart();
          return "redirect:/";
     }
     
     //Update CartItem-Quantity
     @PostMapping("/updateCartItem")
     public String editCartItem(@RequestParam("id") String id, @RequestParam("quantity") String quantity) 
     {
          CartService.editCartItem(id, quantity);
          return "redirect:/cart";
     }
     
     //Remove CartItem from Cart
     @GetMapping("/removeFromCart/{id}")
     public String removeCartItem(@PathVariable String id)
     {
          CartService.removeCartItems(id);
          return "redirect:/cart";
     }

     //----------------------------- CART -----------------------------//


     @GetMapping("/datenschutz")
     public String returnDatenschutz() 
     {
          return "datenschutz";
     }

      //----------------------------- Impressum -----------------------------//

     @GetMapping("/impressum")
     public String returnImpressum() 
     {
          return "impressum";
     }


      //----------------------------- Checkout -----------------------------//
}

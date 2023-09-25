package com.example.demo.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.CartService;
import com.example.demo.service.ProductRequestService;
import com.google.gson.Gson;
import com.example.demo.service.OrderService;
import com.example.demo.models.ProductRequest.Product;


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

     @GetMapping("/test")
    public String test(Model model) {
          Gson gson = new Gson();
          String json = gson.toJson(ProductRequestService.getProductByID("01t68000000XcxPAAS"));
          model.addAttribute("jsonData", json);

          return "test";
    }

     @GetMapping("/login")
     public String login() 
     {
          return "login";
     }

      @GetMapping("/register")
     public String register() 
     {
          return "register";
     }

     //----------------------------- PRODUCTS -----------------------------//

     //Get all Products
     @GetMapping("/products")
     public String viewHomePage(Model model, HttpServletResponse response) 
     {
          List<Product> products = ProductRequestService.getAllProducts();


          model.addAttribute("allProductList", products);

          return "products";
     }

     //Add to Cart
     @PostMapping("/products")
     public String addToCart(@ModelAttribute("product") Product product)
     {
          CartService.addCartItem(product, "1");
          return "redirect:/products";
     }

     //Go to Detailpage
     @GetMapping("/products/{id}")
     public String viewProductDetail(@PathVariable String id, Model model, HttpServletResponse response) 
     {
          model.addAttribute("product", ProductRequestService.getProductByID(id));
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
               OrderService.cancelCheckout();
               CartService.getCart();

          } catch (Exception e)
          {
               CartService.createCart();
          }
          
          model.addAttribute("cartItems", CartService.getCartItems().getCartItems());
          model.addAttribute("cartTotal", CartService.getCart().getGrandTotalAmount());
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

     //----------------------------- Datenschutz -----------------------------//


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

    @GetMapping("/checkout")
    public String startCheckout()
    {
          OrderService.setCheckout();
        return "checkout";
    }

    @PostMapping("/placeOrder")
    public String checkout() 
    {
        return "paymentinfo";
    }

    @PostMapping("/setpayment")
    public String setPayment() 
    {
          OrderService.cancelCheckout();

       return "redirect:/";
    }

    @GetMapping("/cancelCheckout")
    public String cancelCheckout()
    {
        OrderService.cancelCheckout();
        return "redirect:/cart";
    }


    @GetMapping("/widerrufsbelehrung")
    public String viewWiederruf()
    {
        return "widerrufsbelehrung";
    }


}

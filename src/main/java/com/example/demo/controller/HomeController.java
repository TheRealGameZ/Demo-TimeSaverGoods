package com.example.demo.controller;

import com.google.gson.Gson;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.ProductRequestService;
import org.springframework.web.bind.annotation.PostMapping;


import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
     // ----------------------------- HOME -----------------------------//
     @GetMapping("/")
     public String index(Model model, HttpServletResponse response) {
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
     public String login() {
          return "login";
     }

     @GetMapping("/register")
     public String register() {
          return "register";
     }

     @GetMapping("/about")
     public String returnAbout() {
          return "about";
     }

     @GetMapping("/datenschutz")
     public String returnDatenschutz() {
          return "datenschutz";
     }

     @GetMapping("/impressum")
     public String returnImpressum() {
          return "impressum";
     }

     @GetMapping("/widerrufsbelehrung")
     public String viewWiederruf() {
          return "widerrufsbelehrung";
     }

     @PostMapping("/login")
     public String returnuserProfile() {
          return "userProfile";
     }

     @GetMapping("/userProfile")
     public String returnUpfo() {
          return "userProfile";
     }

     @GetMapping("/aboVerwalten")
     public String returnAboVerwalten() {
          return "aboVerwalten";
     }

}

package com.example.demo.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Address;
import com.example.demo.models.CombinedModel;
import com.example.demo.models.PaymentMethod;
import com.example.demo.models.Transformers;
import com.example.demo.service.OrderService;
import com.google.gson.Gson;

@Controller
public class OrderController {

    private String orderNumber;

    private class orderConfirmation {
        public String orderReferenceNumber;
    }

    //Start Checkout
    @GetMapping("/checkout")
    public String startCheckout(Model model) {
        OrderService.setCheckout();
        model.addAttribute("combinedModel", new CombinedModel());
        return "checkout";
    }


    //Get Checkout Info from Form and Place Order
    @PostMapping("/placeOrder")
    public String checkout(@ModelAttribute("combinedModel") CombinedModel combinedModel, Model model) {
        // Extrahieren der Address und PaymentMethod aus dem kombinierten Modell
        Address address = combinedModel.getAddress();
        PaymentMethod paymentMethod = combinedModel.getPaymentMethod();

        CompletableFuture<Void> setAddressFuture = CompletableFuture.runAsync(() -> {
            OrderService.setAddress(Transformers.transformAddress(address));
        });

        setAddressFuture.join();

        OrderService.setPayment(Transformers.transformPayment(address, paymentMethod));

        Gson gson = new Gson();
        this.orderNumber = gson.fromJson(OrderService.setOrder(), orderConfirmation.class).orderReferenceNumber;

        return "redirect:/orderConfirmation";

    }

    // Display orderConfirmation
    @GetMapping("/orderConfirmation")
    public String orderConfirmation(Model model) {

        model.addAttribute("OrderNumber", this.orderNumber);
        return "orderConfirmation";
    }

    // Cancle Checkout
    @GetMapping("/cancelCheckout")
    public String cancelCheckout() {
        OrderService.cancelCheckout();
        return "redirect:/cart";
    }

}

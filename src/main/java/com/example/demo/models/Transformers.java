package com.example.demo.models;

import com.example.demo.service.OrderService;
import com.google.gson.JsonObject;

public class Transformers 
{
    private Transformers() 
    {
    }

    //Address to JSON-String
    public static String transformAddress(Address address) 
    {
        JsonObject deliveryAddress = new JsonObject();
        deliveryAddress.addProperty("name", address.getName());
        deliveryAddress.addProperty("country", address.getCountry());
        deliveryAddress.addProperty("city", address.getCity());
        deliveryAddress.addProperty("street", address.getStreet());
        deliveryAddress.addProperty("postalCode", address.getPostalCode());
    
        JsonObject output = new JsonObject();
        output.add("deliveryAddress", deliveryAddress);
        output.addProperty("shippingInstructions", address.getShippingInstructions());
    
        return output.toString();
    }

    //Payment to JSON-String
    public static String transformPayment( Address address, PaymentMethod paymentMethod) 
    {
        JsonObject cardInfos = new JsonObject();
        cardInfos.addProperty("cardHolderName", paymentMethod.getCardHolderName());
        cardInfos.addProperty("cardNumber", paymentMethod.getCardNumber());
        cardInfos.addProperty("expiryMonth",paymentMethod.getExpiryMonth() );
        cardInfos.addProperty("expiryYear", paymentMethod.getExpiryYear());
        cardInfos.addProperty("cvv", paymentMethod.getCvv());
        cardInfos.addProperty("cardType", "Visa");
    
        JsonObject cardInfosIn = new JsonObject();
        cardInfosIn.add("cardPaymentMethod", cardInfos);


        JsonObject billlingAddress = new JsonObject();
        billlingAddress.addProperty("name", address.getName());
        billlingAddress.addProperty("country", address.getCountry());
        billlingAddress.addProperty("city", address.getCity());
        billlingAddress.addProperty("street", address.getStreet());
        billlingAddress.addProperty("postalCode", address.getPostalCode());

        JsonObject output = new JsonObject();
        output.addProperty("paymentToken", OrderService.getPaymentToken(cardInfosIn.toString()));
        output.addProperty("requestType", "Auth");
        output.add("billingAddress", billlingAddress);

        return output.toString();
      }
}

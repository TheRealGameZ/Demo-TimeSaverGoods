package com.example.demo.service;

import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demo.models.Cart;
import com.example.demo.models.Cart.CartItems;
import com.example.demo.models.Cart.CartItems.CartItem;
import com.example.demo.models.ProductRequest.ProductsPage.Products;
import com.google.gson.Gson;

@Service
public class CartService 
{

    public static Cart getCart() throws HttpClientErrorException
    {
        ResponseEntity<String> response = SalesforceService.salesforceApiCall("/carts/current/", "",HttpMethod.GET);
        Gson gson = new Gson();    

        if(response.getStatusCode().isError())
        {
            throw new HttpClientErrorException(response.getStatusCode());
        }
        else
        {
            return gson.fromJson(response.getBody(),Cart.class);
        }
    }

    public static void createCart()
    { 
        ResponseEntity<String> response = SalesforceService.salesforceApiCall("/carts/active/", "", HttpMethod.PUT);

        if(response.getStatusCode().isError())
        {
            throw new HttpClientErrorException(response.getStatusCode());
        }
    }

    public static void delCart()
    {
        ResponseEntity<String> response = SalesforceService.salesforceApiCall("/carts/active/","",HttpMethod.DELETE);

        if(response.getStatusCode().isError())
        {
            throw new HttpClientErrorException(response.getStatusCode());
        }
    }

    public static Cart getCartItems()
    {
        ResponseEntity<String> response = SalesforceService.salesforceApiCall("/carts/active/cart-items", "", HttpMethod.GET);
        Gson gson = new Gson();

        if(response.getStatusCode().isError()) 
        {
            throw new HttpClientErrorException(response.getStatusCode());
        } 
        
        else return gson.fromJson(response.getBody(),Cart.class);
    }

    public static void addCartItem(Products records, String quantity)
    {
        JSONObject body = new JSONObject();
        body.put("productId", records.getId());
        body.put("quantity", quantity);
        body.put("type", "Product");
            
        ResponseEntity<String> response = SalesforceService.salesforceApiCall("/carts/active/cart-items", body.toString(), HttpMethod.POST);

        if(response.getStatusCode().isError())
        {
            throw new HttpClientErrorException(response.getStatusCode());
        }
    }

    public static void editCartItem(String id, String quantity)
    {
    
        if(quantity.equals("0")) removeCartItems(id);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("quantity", quantity);

        SalesforceService.patchRequest("/carts/active/cart-items/"+id, jsonObject.toString()); 
    }

    public static void removeCartItems(String id)
    {
        ResponseEntity<String> response = SalesforceService.salesforceApiCall("/carts/current/cart-items/"+id,"", HttpMethod.DELETE);

        if(response.getStatusCode().isError())
        {
            throw new HttpClientErrorException(response.getStatusCode());
        }        
    }

    public static CartItem getCartItemByID(String id)
    {
        Cart cart = getCartItems();
        CartItems[] items = cart.getCartItems();

        for(int i = 0; i<items.length; i++)
        {
            CartItem current = items[i].getCartItem();
            
            if(current.getCartItemId().equalsIgnoreCase(id)) return current;
        }

        return null;
    }

}
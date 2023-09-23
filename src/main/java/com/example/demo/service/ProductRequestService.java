package com.example.demo.service;


import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demo.models.ProductRequest;
import com.example.demo.models.ProductRequest.Product;
import com.google.gson.Gson;


@Service
public class ProductRequestService 
{
    
    public static List<Product> getAllProducts()
    {
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("categoryId", "0ZG68000000LRpYGAW");
        jsonObject.put("includePrices", "true");

        ResponseEntity<String> response = SalesforceService.salesforceApiCall("/search/product-search", jsonObject.toString(), HttpMethod.POST);
       
        if(response.getStatusCode().isError())
        {
            throw new HttpClientErrorException(response.getStatusCode());
        }
        else
        {
            ProductRequest request = gson.fromJson(response.getBody(),ProductRequest.class);
            return request.productsPage.products;
        }
    }

    public static List<Product> getSomeProducts()
    {
        List<Product> products = ProductRequestService.getAllProducts();

        return products.subList(0, 4);

    }

    public static Product getProductByID(String id)
    {
        Gson gson = new Gson();
        ResponseEntity<String> response = SalesforceService.salesforceApiCall("/products/"+id, "", HttpMethod.GET);
        
        if(response.getStatusCode().isError())
        {
            throw new HttpClientErrorException(response.getStatusCode());
        }
        else
        {
            return gson.fromJson(response.getBody(),Product.class);
        }
    }

}

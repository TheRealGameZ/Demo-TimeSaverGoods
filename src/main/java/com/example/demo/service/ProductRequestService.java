package com.example.demo.service;


import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demo.models.ProductRequest;
import com.example.demo.models.ProductRequest.ProductsPage.Products;
import com.google.gson.Gson;


@Service
public class ProductRequestService 
{
    
    public static List<Products> getAllProducts()
    {
        Gson gson = new Gson();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("categoryId", "0ZG68000000LRpYGAW");

        ResponseEntity<String> response = SalesforceService.salesforceApiCall("/search/product-search", jsonObject.toString(), HttpMethod.POST);
       
        if(response.getStatusCode().isError())
        {
            throw new HttpClientErrorException(response.getStatusCode());
        }
        else
        {
            ProductRequest request = gson.fromJson(response.getBody(),ProductRequest.class);
            List<Products> mylist = Arrays.asList(request.getProductsPage().getProducts());

            return mylist;
        }
    }

    public static List<Products> getSomeProducts()
    {
        List<Products> products = ProductRequestService.getAllProducts();

        return products.subList(0, 8);

    }

    public static Products getProductByID(String id)
    {
        Gson gson = new Gson();
        ResponseEntity<String> response = SalesforceService.salesforceApiCall("/products/"+id, "", HttpMethod.GET);
        
        if(response.getStatusCode().isError())
        {
            throw new HttpClientErrorException(response.getStatusCode());
        }
        else
        {
            return gson.fromJson(response.getBody(),Products.class);
        }
    }

}

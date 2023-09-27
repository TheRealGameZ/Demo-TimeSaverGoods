package com.example.demo.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.json.JSONObject;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.example.demo.models.PriceInfo;
import com.example.demo.models.ProductData;
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
            ProductRequest request = gson.fromJson(response.getBody(), ProductRequest.class);

            for(Product product: request.productsPage.products){
                product.defaultImage.url = "images/"+product.name.substring(0,4) + ".jpeg";
            }


            return request.productsPage.products;
        }
    }

    public static List<Product> getSomeProducts()
    {
        List<Product> products = ProductRequestService.getAllProducts();

        return products.subList(0, 4);
    }

    public static ProductData getProductByID(String id)
    {
        Gson gson = new Gson();
        ResponseEntity<String> response = SalesforceService.salesforceApiCall("/products/"+id, "", HttpMethod.GET);
        ResponseEntity<String> responsePrice = SalesforceService.salesforceApiCall("/pricing/products/"+id, "", HttpMethod.GET);
        
        if(response.getStatusCode().isError() || responsePrice.getStatusCode().isError())
        {
            throw new HttpClientErrorException(response.getStatusCode());
        }
        else
        {
            PriceInfo price = gson.fromJson(responsePrice.getBody(), PriceInfo.class);
            ProductData productData = gson.fromJson(response.getBody(), ProductData.class);
            productData.setUnitPrice(price.getUnitPrice());
            return productData;
        }
    }

    /* private static Map<String, String> checkImages(Map<String, String> urls){
        String path = "src/main/resources/static/images/";
        List<String> downloadableImages = new ArrayList<>();
        Map<String, String> returnUrls = new HashMap<>();

        for(String url : urls.keySet()){
            File image = new File(path + urls.get(url).substring(0, 5) + ".png");
            
            if(!image.exists()){
                downloadableImages.add(url);
            }
        }

        for(String url: downloadableImages){
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);

            try {
                HttpResponse response = httpClient.execute(httpGet);
                byte[] imageBytes = EntityUtils.toByteArray(response.getEntity());

                Path filePath = Paths.get("src/main/resources/static/images/", urls.get(url).substring(0, 5) + ".png");

                Files.write(filePath, imageBytes);

                returnUrls.put(urls.get(url), "images/" + urls.get(url).substring(0, 5) + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return returnUrls;
    } */
}

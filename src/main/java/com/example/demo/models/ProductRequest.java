package com.example.demo.models;

import java.util.List;

public class ProductRequest {
    public class Product {
        public String id;
        public String name;
        public DefaultImage defaultImage;
        public Fields fields;
        public String productClass;
        public String purchaseQuantityRule;
        public Object variationAttributeSet;
        public Prices prices;
    }

    public class DefaultImage {
        public String alternateText;
        public Object contentVersionId;
        public Object id;
        public String mediaType;
        public int sortOrder;
        public Object thumbnailUrl;
        public String title;
        public String url;
    }

    public class Fields {
        public Description Description;
    }

    public class Description {
        public String value;
    }

    public class Prices {
        public Object error;
        public Object listPrice;
        public String pricebookEntryId;
        public String productId;
        public boolean success;
        public String unitPrice;
    }

    public class ProductsPage {
        public String currencyIsoCode;
        public int pageSize;
        public List<Product> products;
        public int total;
    }

    public ProductsPage productsPage;
}
